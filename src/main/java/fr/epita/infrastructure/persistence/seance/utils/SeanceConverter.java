package fr.epita.infrastructure.persistence.seance.utils;

import java.util.UUID;

import fr.epita.domain.seance.model.Seance;
import fr.epita.infrastructure.persistence.seance.entity.SeanceJPAEntity;

public class SeanceConverter {

    public static SeanceJPAEntity seanceJPAEntityFromDomain(Seance seance) {
        return new SeanceJPAEntity(
            (seance.getId() != null ? seance.getId().toString() : null),
            seance.getName(),
            seance.getDate(),
            seance.getDurationInHours(),
            SalleConverter.SalleJPAEntityfromDomain(seance.getSalle())
        );
    }

    public static Seance seanceFromSeanceJPAEntity(SeanceJPAEntity seanceJPAEntity) {
        return new Seance(
            UUID.fromString(seanceJPAEntity.getId()),
            seanceJPAEntity.getName(),
            seanceJPAEntity.getDate(),
            seanceJPAEntity.getDurationInHours(),
            SalleConverter.salleFromSalleJPAEntity(seanceJPAEntity.getSalle())
        );
    }

    public static SeanceJPAEntity updateSeanceJPAEntityFromDomain(Seance seance, SeanceJPAEntity seanceJPAEntity) {
        seanceJPAEntity.setName(seance.getName());
        seanceJPAEntity.setDate(seance.getDate());
        seanceJPAEntity.setDurationInHours(seance.getDurationInHours());
        seanceJPAEntity.setSalle(SalleConverter.SalleJPAEntityfromDomain(seance.getSalle()));
        return seanceJPAEntity;
    }
}
