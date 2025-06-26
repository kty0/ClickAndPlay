package fr.epita.infrastructure.persistence.table.entity;

import fr.epita.infrastructure.persistence.seance.entity.SeanceJPAEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tables")
public class TableJPAEntity {
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "seance_id", nullable = false)
    private SeanceJPAEntity seance;
    @Column
    private String gameName;
    @Column
    private int maxPlayers;
    @Column
    private LocalDateTime startDateTime;
    @Column
    private int estimatedDurationInHours;

    public TableJPAEntity() {}

    public TableJPAEntity(String id, SeanceJPAEntity seance, String gameName, int maxPlayers,
                          LocalDateTime startDateTime, int estimatedDurationInHours) {
        this.id = id;
        this.seance = seance;
        this.gameName = gameName;
        this.maxPlayers = maxPlayers;
        this.startDateTime = startDateTime;
        this.estimatedDurationInHours = estimatedDurationInHours;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public SeanceJPAEntity getSeance() {
        return seance;
    }
    public void setSeance(SeanceJPAEntity seance) {
        this.seance = seance;
    }
    public String getGameName() {
        return gameName;
    }
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
    public int getEstimatedDurationInHours() {
        return estimatedDurationInHours;
    }
    public void setEstimatedDurationInHours(int estimatedDurationInHours) {
        this.estimatedDurationInHours = estimatedDurationInHours;
    }
}
