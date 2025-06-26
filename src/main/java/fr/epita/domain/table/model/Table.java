package fr.epita.domain.table.model;

import java.time.LocalDateTime;
import java.util.UUID;

import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.table.exception.TableException;

public class Table {
    private UUID id;
    private Seance seance;
    private String gameName;
    private int maxPlayers;
    private LocalDateTime startDateTime;
    private int estimatedDurationInHours;

    public Table(UUID id, Seance seance, String gameName, int maxPlayers, LocalDateTime startDateTime, int estimatedDurationInHours) {
        if (id == null) {
            throw new TableException("Id must not be null");
        }
        this.id = id;
        this.seance = seance;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;

        validate();
    }

    public Table(Seance seance, String gameName, int maxPlayers,  LocalDateTime startDateTime, int estimatedDurationInHours) {
        this.seance = seance;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;

        validate();
    }

    private void validate() {
        if (seance == null) {
            throw new TableException("Seance must not be null");
        }
        if (gameName == null || gameName.isBlank()) {
            throw new TableException("Game name must not be null or blank");
        }
        if (maxPlayers <= 0) {
            throw new TableException("Max players must be greater than 0");
        }
        if (startDateTime == null || startDateTime.isBefore(LocalDateTime.now())) {
            throw new TableException("Start date and time must be in the future");
        }
        if (estimatedDurationInHours <= 0) {
            throw new TableException("Estimated duration in hours must be positive");
        }
    }

    public UUID getId() {
        return id;
    }

    public Seance getSeance() {
        return seance;
    }

    public String getGameName() {
        return gameName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public int getEstimatedDurationInHours() {
        return estimatedDurationInHours;
    }
}
