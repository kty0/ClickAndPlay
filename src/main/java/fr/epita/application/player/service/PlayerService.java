package fr.epita.application.player.service;

import fr.epita.application.player.exception.PlayerNotFoundException;
import fr.epita.application.player.utils.PlayerConverter;
import fr.epita.domain.player.model.Player;
import fr.epita.domain.player.port.PlayerRepository;
import fr.epita.presentation.player.dto.PlayerCreateDto;
import fr.epita.presentation.player.dto.PlayerDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public PlayerDto createPlayer(final PlayerCreateDto playerCreateDto) {
        Player player = PlayerConverter.newPlayerFromPlayerDTO(playerCreateDto);
        Player savedPlayer = playerRepository.save(player);
        return PlayerConverter.playerDTOFromPlayer(savedPlayer);
    }

    @Transactional
    public PlayerDto updatePlayer(final PlayerDto playerDto) {
        Player player = PlayerConverter.playerFromPlayerDTO(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return PlayerConverter.playerDTOFromPlayer(savedPlayer);
    }

    @Transactional
    public void deletePlayer(final String id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + id));
        playerRepository.delete(player);
    }

    @Transactional
    public PlayerDto getPlayer(final String id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + id));
        return PlayerConverter.playerDTOFromPlayer(player);
    }

    @Transactional
    public List<PlayerDto> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDTOs = new ArrayList<>();
        for (Player player : players) {
            playerDTOs.add(PlayerConverter.playerDTOFromPlayer(player));
        }
        return playerDTOs;
    }

    @Transactional
    public PlayerDto becomeMember(final String id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id : " + id));

        player.becomeMember();
        Player savedPlayer = playerRepository.save(player);
        return PlayerConverter.playerDTOFromPlayer(savedPlayer);
    }
}
