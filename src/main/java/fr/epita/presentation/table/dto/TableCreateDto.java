package fr.epita.presentation.table.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TableCreateDto {
    private UUID seanceId;
    private String gameName;
    private int maxPlayers;
    private LocalDateTime startDateTime;
    private int estimatedDurationInHours;

    public TableCreateDto(UUID seanceId, String gameName, int maxPlayers, LocalDateTime startDateTime, int estimatedDurationInHours) {
        this.seanceId = seanceId;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;
    }

    public UUID getSeanceId() {
        return seanceId;
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
