package fr.epita.infrastructure.persistence.table.utils;

import fr.epita.domain.player.model.Player;
import fr.epita.domain.table.model.Table;
import fr.epita.infrastructure.persistence.player.entity.PlayerJPAEntity;
import fr.epita.infrastructure.persistence.player.utils.PlayerConverter;
import fr.epita.infrastructure.persistence.seance.entity.SeanceJPAEntity;
import fr.epita.infrastructure.persistence.seance.utils.SeanceConverter;
import fr.epita.infrastructure.persistence.table.entity.TableJPAEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TableConverter {

    public static TableJPAEntity tableJPAEntityFromDomain(Table table, SeanceJPAEntity seanceJPAEntity) {
       return new TableJPAEntity(
               (table.getId() != null ? table.getId().toString() : null),
               seanceJPAEntity,
               table.getGameName(),
               table.getMaxPlayers(),
               table.getStartDateTime(),
               table.getEstimatedDurationInHours(),
               table.isFree()
       );
    }

    public static Table tableFromTableJPAEntity(TableJPAEntity tableJPAEntity) {
        Table table = new Table(
                UUID.fromString(tableJPAEntity.getId()),
                SeanceConverter.seanceFromSeanceJPAEntity(tableJPAEntity.getSeance()),
                tableJPAEntity.getGameName(),
                tableJPAEntity.getMaxPlayers(),
                tableJPAEntity.getStartDateTime(),
                tableJPAEntity.getEstimatedDurationInHours(),
                tableJPAEntity.isFree()
        );
        if (tableJPAEntity.getPlayers() != null && !tableJPAEntity.getPlayers().isEmpty()) {
            Set<Player> domainPlayers = tableJPAEntity.getPlayers().stream()
                    .map(PlayerConverter::playerFromPlayerJPAEntityWithoutTables)
                    .collect(Collectors.toSet());
            table.setPlayers(domainPlayers);
        }
        return table;
    }

    public static TableJPAEntity updateTableJPAEntityFromDomain(Table table, TableJPAEntity tableJPAEntity, SeanceJPAEntity seanceJPAEntity) {
        tableJPAEntity.setSeance(seanceJPAEntity);
        tableJPAEntity.setGameName(table.getGameName());
        tableJPAEntity.setMaxPlayers(table.getMaxPlayers());
        tableJPAEntity.setStartDateTime(table.getStartDateTime());
        tableJPAEntity.setEstimatedDurationInHours(table.getEstimatedDurationInHours());
        tableJPAEntity.setFree(table.isFree());
        Set<PlayerJPAEntity> updatedPlayersJPA = new HashSet<>();
        if (table.getPlayers() != null) {
            for (Player domainPlayer : table.getPlayers()) {
                updatedPlayersJPA.add(PlayerConverter.playerJPAEntityFromDomain(domainPlayer));
            }
        }
        tableJPAEntity.setPlayers(updatedPlayersJPA);
        return tableJPAEntity;
    }

    public static Table tableFromTableJPAEntityWithoutPlayers(TableJPAEntity tableJPAEntity) {
        return new Table(
                SeanceConverter.seanceFromSeanceJPAEntity(tableJPAEntity.getSeance()),
                tableJPAEntity.getGameName(),
                tableJPAEntity.getMaxPlayers(),
                tableJPAEntity.getStartDateTime(),
                tableJPAEntity.getEstimatedDurationInHours(),
                tableJPAEntity.isFree()
        );
    }
}
