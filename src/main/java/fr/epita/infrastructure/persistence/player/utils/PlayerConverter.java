package fr.epita.infrastructure.persistence.player.utils;

import fr.epita.domain.player.model.Player;
import fr.epita.infrastructure.persistence.player.entity.PlayerJPAEntity;

import java.util.UUID;

public class PlayerConverter {
    public static PlayerJPAEntity playerJPAEntityFromDomain(Player player) {
        return new PlayerJPAEntity(
                (player.getId() != null ? player.getId().toString() : null),
                player.getEmail(),
                player.isMember(),
                player.isFirstSeance()
        );
    }

    public static Player playerFromPlayerJPAEntity(PlayerJPAEntity playerJPAEntity) {
        return new Player(
                UUID.fromString(playerJPAEntity.getId()),
                playerJPAEntity.getEmail(),
                playerJPAEntity.isMember(),
                playerJPAEntity.isFirstSeance()
        );
    }

    public static PlayerJPAEntity updatePlayerJPAEntityFromDomain(Player player, PlayerJPAEntity playerJPAEntity) {
        playerJPAEntity.setEmail(player.getEmail());
        playerJPAEntity.setMember(player.isMember());
        playerJPAEntity.setFirstSeance(player.isFirstSeance());
        return playerJPAEntity;
    }
}
