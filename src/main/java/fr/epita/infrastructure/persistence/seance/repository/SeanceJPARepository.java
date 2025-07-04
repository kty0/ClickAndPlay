package fr.epita.infrastructure.persistence.seance.repository;

import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.seance.port.SeanceRepository;
import fr.epita.infrastructure.persistence.player.entity.PlayerJPAEntity;
import fr.epita.infrastructure.persistence.seance.entity.SeanceJPAEntity;
import fr.epita.infrastructure.persistence.seance.utils.SeanceConverter;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class SeanceJPARepository implements SeanceRepository {

    private final SpringDataSeanceRepository springDataJpaSeanceRepository;

    public SeanceJPARepository(SpringDataSeanceRepository springDataJpaSeanceRepository) {
        this.springDataJpaSeanceRepository = springDataJpaSeanceRepository;
    }

    @Override
    public Seance save(Seance seance) {
        SeanceJPAEntity seanceJPAEntity;
        if (seance.getId() == null) {
            seanceJPAEntity = SeanceConverter.seanceJPAEntityFromDomain(seance);
        } else {
            seanceJPAEntity = springDataJpaSeanceRepository.findById(seance.getId().toString())
                    .orElseThrow(() -> new EntityNotFoundException("SeanceJPAEntity not found with id: " + seance.getId()));
            seanceJPAEntity = SeanceConverter.updateSeanceJPAEntityFromDomain(seance, seanceJPAEntity);
        }
        SeanceJPAEntity savedSeanceJPAEntity = springDataJpaSeanceRepository.save(seanceJPAEntity);
        return SeanceConverter.seanceFromSeanceJPAEntity(savedSeanceJPAEntity);
    }

    @Override
    public Optional<Seance> findById(String id) {
        return springDataJpaSeanceRepository.findById(id).map(SeanceConverter::seanceFromSeanceJPAEntity);
    }

    @Override
    public List<Seance> findAllByName(String name) {
        return springDataJpaSeanceRepository.findAllByName(name)
                .stream()
                .map(SeanceConverter::seanceFromSeanceJPAEntity)
                .toList();
    }

    @Override
    public void delete(Seance seance) {
        if (seance.getId() == null) {
            throw new IllegalArgumentException("Seance id cannot be null for deletion");
        }
        springDataJpaSeanceRepository.deleteById(seance.getId().toString());
    }

    @Override
    public Seance update(Seance seance) {
        if (seance.getId() == null) {
            throw new IllegalArgumentException("Seance id cannot be null for update");
        }
        SeanceJPAEntity seanceJPAEntity = springDataJpaSeanceRepository.findById(seance.getId().toString())
                .orElseThrow(() -> new EntityNotFoundException("SeanceJPAEntity not found with id: " + seance.getId()));
        seanceJPAEntity = SeanceConverter.updateSeanceJPAEntityFromDomain(seance, seanceJPAEntity);
        SeanceJPAEntity updatedSeanceJPAEntity = springDataJpaSeanceRepository.save(seanceJPAEntity);
        return SeanceConverter.seanceFromSeanceJPAEntity(updatedSeanceJPAEntity);
    }

    @Override
    public List<Seance> findAll() {
        return springDataJpaSeanceRepository.findAll()
                .stream()
                .map(SeanceConverter::seanceFromSeanceJPAEntity)
                .toList();
    }

    @Override
    public List<String> getEmails(Seance seance) {
        SeanceJPAEntity seanceJPAEntity = springDataJpaSeanceRepository.findById(seance.getId().toString())
                .orElseThrow(() -> new EntityNotFoundException("SeanceJPAEntity not found with id: " + seance.getId()));
        return seanceJPAEntity.getTables().stream()
                .flatMap(table -> table.getPlayers().stream())
                .map(PlayerJPAEntity::getEmail)
                .distinct()
                .collect(Collectors.toList());
    }
}
