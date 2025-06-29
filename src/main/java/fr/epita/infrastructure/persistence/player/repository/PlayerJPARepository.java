package fr.epita.infrastructure.persistence.player.repository;

import fr.epita.domain.player.model.Player;
import fr.epita.domain.player.port.PlayerRepository;
import fr.epita.infrastructure.persistence.player.entity.PlayerJPAEntity;
import fr.epita.infrastructure.persistence.player.utils.PlayerConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface SpringDataPlayerRepository extends JpaRepository<PlayerJPAEntity, String> {
}

@Repository
public class PlayerJPARepository implements PlayerRepository {
    private final SpringDataPlayerRepository springDataPlayerRepository;

    public PlayerJPARepository(SpringDataPlayerRepository springDataPlayerRepository) {
        this.springDataPlayerRepository = springDataPlayerRepository;
    }

    @Override
    public Player save(Player player) {
        PlayerJPAEntity playerJPAEntity;
        if (player.getId() == null) {
            playerJPAEntity = PlayerConverter.playerJPAEntityFromDomain(player);
        } else {
            playerJPAEntity = springDataPlayerRepository.findById(player.getId().toString())
                    .orElseThrow(() -> new EntityNotFoundException("PlayerJPAEntity not found with id " + player.getId()));
            playerJPAEntity = PlayerConverter.updatePlayerJPAEntityFromDomain(player, playerJPAEntity);
        }
        PlayerJPAEntity savedPlayerJPAEntity = springDataPlayerRepository.save(playerJPAEntity);
        return PlayerConverter.playerFromPlayerJPAEntity(savedPlayerJPAEntity);
    }

    @Override
    public Optional<Player> findById(String id) {
        return springDataPlayerRepository.findById(id)
                .map(PlayerConverter::playerFromPlayerJPAEntity);
    }

    @Override
    public List<Player> findAll() {
        return springDataPlayerRepository.findAll()
                .stream()
                .map(PlayerConverter::playerFromPlayerJPAEntity)
                .toList();
    }

    @Override
    public void delete(Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("Player id cannot be null for deletion");
        }
        springDataPlayerRepository.deleteById(player.getId().toString());
    }

    @Override
    public Player update(Player player) {
        if (player.getId() == null) {
            throw new IllegalArgumentException("Player id cannot be null for update");
        }
        PlayerJPAEntity playerJPAEntity = springDataPlayerRepository.findById(player.getId().toString())
                .orElseThrow(() -> new EntityNotFoundException("PlayerJPAEntity not found with id " + player.getId()));
        playerJPAEntity = PlayerConverter.updatePlayerJPAEntityFromDomain(player, playerJPAEntity);
        PlayerJPAEntity updatedPlayerJPAEntity = springDataPlayerRepository.save(playerJPAEntity);
        return PlayerConverter.playerFromPlayerJPAEntity(updatedPlayerJPAEntity);
    }
}
