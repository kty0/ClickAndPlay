package fr.epita.domain.seance.port;

import fr.epita.domain.seance.model.Seance;

import java.util.List;
import java.util.Optional;

public interface SeanceRepository {
    Seance save(Seance seance);
    Optional<Seance> findById(String id);
    List<Seance> findAllByName(String name);
    void delete(Seance seance);
    Seance update(Seance seance);
    List<Seance> findAll();
}