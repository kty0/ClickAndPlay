package fr.epita.domain.registration.service;

import fr.epita.domain.player.model.Player;
import fr.epita.domain.registration.exception.RegistrationException;
import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.table.model.Table;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class TableRegistrationVerificationService {
    public void checkTableOverlap(Player player, Table tableToRegister, Set<Table> currentTables) {
        LocalDateTime newTableStartTime = tableToRegister.getStartDateTime();
        int newTableDuration = tableToRegister.getEstimatedDurationInHours();
        LocalDateTime newTableEndTime = newTableStartTime.plusHours(newTableDuration);

        for (Table existingTable : currentTables) {
            LocalDateTime existingTableStartTime = existingTable.getStartDateTime();
            int existingTableDuration = existingTable.getEstimatedDurationInHours();
            LocalDateTime existingTableEndTime = existingTableStartTime.plusHours(existingTableDuration);

            boolean overlaps = (newTableStartTime.isBefore(existingTableEndTime) && existingTableStartTime.isBefore(newTableEndTime));
            if (overlaps) {
                throw new RegistrationException("Player " + player.getId() + " cannot register for table " + tableToRegister.getGameName() + " as it overlaps with registered tables");
            }
        }
    }

    public Player checkPlayerFirstSeanceAndMember(Player player, Table tableToRegister) {
        Set<Seance> playerRegisteredSeances = player.getTables().stream()
                .map(Table::getSeance)
                .collect(Collectors.toSet());

        boolean isNewSeanceForPlayer = !playerRegisteredSeances.contains(tableToRegister.getSeance());

        if (isNewSeanceForPlayer) {
            if (!player.isFirstSeanceUsed()) {
                player.useFirstSeance();
            } else if (!player.isMember()) {
                throw new RegistrationException("Player " + player.getId() + " is not a member and has already used his first seance");
            }
        }
        return player;
    }
}
