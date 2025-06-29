package fr.epita.presentation.table.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class TableDto {
    private UUID id;
    private UUID seanceId;
    private String gameName;
    private int maxPlayers;
    private LocalDateTime startDateTime;
    private int estimatedDurationInHours;
    private boolean free;
    private int remainingCapacity;

    public TableDto(UUID id, UUID seanceId, String gameName, int maxPlayers, LocalDateTime startDateTime, int estimatedDurationInHours, boolean free, int remainingCapacity) {
        this.id = id;
        this.seanceId = seanceId;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;
        this.free = free;
        this.remainingCapacity = remainingCapacity;
    }

    public UUID getId() {
        return id;
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

    public boolean isFree() {
        return free;
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }
}
