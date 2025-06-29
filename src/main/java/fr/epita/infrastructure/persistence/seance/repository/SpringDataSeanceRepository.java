package fr.epita.infrastructure.persistence.seance.repository;

import fr.epita.infrastructure.persistence.seance.entity.SeanceJPAEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataSeanceRepository extends JpaRepository<SeanceJPAEntity, String> {
    List<SeanceJPAEntity> findAllByName(String name);
}
