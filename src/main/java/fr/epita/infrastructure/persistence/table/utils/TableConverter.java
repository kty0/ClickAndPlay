package fr.epita.infrastructure.persistence.table.utils;

import fr.epita.domain.table.model.Table;
import fr.epita.infrastructure.persistence.seance.utils.SeanceConverter;
import fr.epita.infrastructure.persistence.table.entity.TableJPAEntity;

import java.util.UUID;

public class TableConverter {

    public static TableJPAEntity tableJPAEntityFromDomain(Table table) {
       return new TableJPAEntity(
               (table.getId() != null ? table.getId().toString() : null),
               SeanceConverter.seanceJPAEntityFromDomain(table.getSeance()),
               table.getGameName(),
               table.getMaxPlayers(),
               table.getStartDateTime(),
               table.getEstimatedDurationInHours()
       );
    }

    public static Table tableFromTableJPAEntity(TableJPAEntity tableJPAEntity) {
        return new Table(
                UUID.fromString(tableJPAEntity.getId()),
                SeanceConverter.seanceFromSeanceJPAEntity(tableJPAEntity.getSeance()),
                tableJPAEntity.getGameName(),
                tableJPAEntity.getMaxPlayers(),
                tableJPAEntity.getStartDateTime(),
                tableJPAEntity.getEstimatedDurationInHours()
        );
    }

    public static TableJPAEntity updateTableJPAEntityFromDomain(Table table, TableJPAEntity tableJPAEntity) {
        tableJPAEntity.setSeance(SeanceConverter.seanceJPAEntityFromDomain(table.getSeance()));
        tableJPAEntity.setGameName(table.getGameName());
        tableJPAEntity.setMaxPlayers(table.getMaxPlayers());
        tableJPAEntity.setStartDateTime(table.getStartDateTime());
        tableJPAEntity.setEstimatedDurationInHours(table.getEstimatedDurationInHours());
        return tableJPAEntity;
    }
}
