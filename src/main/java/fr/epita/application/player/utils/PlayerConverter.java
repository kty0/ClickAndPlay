package fr.epita.application.player.utils;

import fr.epita.domain.player.model.Player;
import fr.epita.presentation.player.dto.PlayerCreateDto;
import fr.epita.presentation.player.dto.PlayerDto;

public class PlayerConverter {
    public static Player newPlayerFromPlayerDTO(PlayerCreateDto playerCreateDto) {
        return new Player(
                playerCreateDto.getEmail(),
                playerCreateDto.getRoles(),
                false,
                false
        );
    }

    public static Player playerFromPlayerDTO(PlayerDto playerDto) {
        return new Player(
                playerDto.getId(),
                playerDto.getEmail(),
                playerDto.getRoles(),
                playerDto.getMember(),
                playerDto.getFirstSeance()
        );
    }

    public static PlayerDto playerDTOFromPlayer(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getEmail(),
                player.getRoles(),
                player.getMember(),
                player.getFirstSeance()
        );
    }
}
