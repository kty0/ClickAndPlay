package fr.epita.domain.seance.model;

import fr.epita.domain.seance.exception.SeanceException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Seance {
    private UUID id;
    private String name;
    private LocalDateTime date;
    private int durationInHours;
    private Salle salle;

    public Seance(UUID id, String name, LocalDateTime date, int durationInHours, Salle salle) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.durationInHours = durationInHours;
        this.salle = salle;

        if (id == null) {
            throw new SeanceException("Id must not be null");
        }

        validate();
    }

    public Seance(String name, LocalDateTime date, int durationInHours, Salle salle) {
        this.name = name;
        this.date = date;
        this.durationInHours = durationInHours;
        this.salle = salle;
        validate();
    }

    private void validate() {
        if (name == null || name.isBlank()) {
            throw new SeanceException("name must not be null");
        }
        if (date == null) {
            throw new SeanceException("date must not be null");
        }
        if (durationInHours <= 0) {
            throw new SeanceException("durationInHours must be positive");
        }
        if (salle == null) {
            throw new SeanceException("salle must not be null");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new SeanceException("date must be in the future");
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return id != null && id.equals(seance.id);
    }
}
