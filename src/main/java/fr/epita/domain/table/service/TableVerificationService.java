package fr.epita.domain.table.service;

import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.table.exception.TableException;
import fr.epita.domain.table.model.Table;

import java.time.LocalDateTime;
import java.util.List;

public class TableVerificationService {
    public void verifyTable(List<Table> tables, Seance seance) {
        int totalCapacityUsed = 0;
        for (Table table : tables) {
            verifyStartTimeWithinSeance(table, seance);
            verifyEndBeforeSeanceEnds(table, seance);

            totalCapacityUsed += table.getMaxPlayers();
        }

        if (totalCapacityUsed > seance.getSalle().capacity()) {
            throw new TableException("Sum of the maximum number of players: " + totalCapacityUsed + " of tables capacity must not exceed room capacity: " + seance.getSalle().capacity());
        }
    }

    private void verifyStartTimeWithinSeance(Table table, Seance seance) {
        LocalDateTime seanceStart = seance.getDate();
        LocalDateTime seanceEnd = seanceStart.plusHours(seance.getDurationInHours());
        LocalDateTime tableStart = table.getStartDateTime();

        if (tableStart.isBefore(seanceStart) || tableStart.isAfter(seanceEnd)) {
            throw new TableException("Table start date and time must be within seance date and time");
        }
    }

    private void verifyEndBeforeSeanceEnds(Table table, Seance seance) {
        LocalDateTime tableEnd = table.getStartDateTime().plusHours(table.getEstimatedDurationInHours());
        LocalDateTime seanceEnd = seance.getDate().plusHours(seance.getDurationInHours()).minusMinutes(15);

        if (tableEnd.isAfter(seanceEnd)) {
            throw new TableException("Table must finish 15 minutes before seance ends");
        }
    }
}
