package fr.epita.infrastructure.persistence.player.utils;

import fr.epita.domain.player.model.Player;
import fr.epita.domain.table.model.Table;
import fr.epita.infrastructure.persistence.player.entity.PlayerJPAEntity;
import fr.epita.infrastructure.persistence.table.utils.TableConverter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlayerConverter {
    public static PlayerJPAEntity playerJPAEntityFromDomain(Player player) {
        return new PlayerJPAEntity(
                (player.getId() != null ? player.getId().toString() : null),
                player.getEmail(),
                player.isMember(),
                player.isFirstSeanceUsed()
        );
    }

    public static Player playerFromPlayerJPAEntity(PlayerJPAEntity playerJPAEntity) {
        Player player = new Player(
                UUID.fromString(playerJPAEntity.getId()),
                playerJPAEntity.getEmail(),
                playerJPAEntity.isMember(),
                playerJPAEntity.isFirstSeanceUsed()
        );
        if (playerJPAEntity.getTables() != null && !playerJPAEntity.getTables().isEmpty()) {
            Set<Table> domainTables = playerJPAEntity.getTables().stream()
                    .map(TableConverter::tableFromTableJPAEntityWithoutPlayers)
                    .collect(Collectors.toSet());
            player.setTables(domainTables);
        }
        return player;
    }

    public static PlayerJPAEntity updatePlayerJPAEntityFromDomain(Player player, PlayerJPAEntity playerJPAEntity) {
        playerJPAEntity.setEmail(player.getEmail());
        playerJPAEntity.setMember(player.isMember());
        playerJPAEntity.setFirstSeanceUsed(player.isFirstSeanceUsed());
        return playerJPAEntity;
    }

    public static Player playerFromPlayerJPAEntityWithoutTables(PlayerJPAEntity playerJPAEntity) {
        return new Player(
                UUID.fromString(playerJPAEntity.getId()),
                playerJPAEntity.getEmail(),
                playerJPAEntity.isMember(),
                playerJPAEntity.isFirstSeanceUsed()
        );
    }
}
