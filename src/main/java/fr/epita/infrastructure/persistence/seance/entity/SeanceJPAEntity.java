package fr.epita.infrastructure.persistence.seance.entity;

import java.time.LocalDateTime;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "seances")
public class SeanceJPAEntity {
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column
    private String name;
    @Column
    private LocalDateTime date;
    @Column(name = "duration_in_hours")
    private int durationInHours;
    @Embedded
    private SalleJPAEntity salle;

    public SeanceJPAEntity() {
    }

    public SeanceJPAEntity(String id, String name, LocalDateTime date, int durationInHours, SalleJPAEntity salle) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.durationInHours = durationInHours;
        this.salle = salle;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public int getDurationInHours() {
        return durationInHours;
    }
    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }
    public SalleJPAEntity getSalle() {
        return salle;
    }
    public void setSalle(SalleJPAEntity salle) {
        this.salle = salle;
    }
}
