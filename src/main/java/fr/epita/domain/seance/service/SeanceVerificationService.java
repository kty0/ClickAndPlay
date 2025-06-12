package fr.epita.domain.seance.service;

import fr.epita.domain.seance.exception.SeanceException;
import fr.epita.domain.seance.model.Seance;

import java.time.LocalDateTime;
import java.util.List;

public class SeanceVerificationService {

    public void checkSeanceOverlap(Seance seance, List<Seance> seances) {
        for (Seance s : seances) {
            LocalDateTime endTime = seance.getDate().plusHours(seance.getDurationInHours());
            if (seance.getDate().equals(s.getDate()) || seance.getDate().isBefore(endTime)) {
                throw new SeanceException("Seances can't be overlapped");
            }
        }
    }
}
