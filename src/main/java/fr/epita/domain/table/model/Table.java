package fr.epita.domain.table.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import fr.epita.domain.player.model.Player;
import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.table.exception.TableException;

public class Table {
    private UUID id;
    private Seance seance;
    private String gameName;
    private int maxPlayers;
    private LocalDateTime startDateTime;
    private int estimatedDurationInHours;
    private boolean free;
    private Set<Player> players = new HashSet<>();

    public Table(UUID id, Seance seance, String gameName, int maxPlayers, LocalDateTime startDateTime, int estimatedDurationInHours, boolean free) {
        if (id == null) {
            throw new TableException("Id must not be null");
        }
        this.id = id;
        this.seance = seance;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;
        this.free = free;

        validate();
    }

    public Table(Seance seance, String gameName, int maxPlayers, LocalDateTime startDateTime, int estimatedDurationInHours, boolean free) {
        this.seance = seance;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;
        this.free = free;

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

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    public boolean isFull() {
        return players.size() == maxPlayers;
    }

    public boolean isPlayerRegistered(Player player) {
        return players.contains(player);
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

    public boolean isFree() {
        return free;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
