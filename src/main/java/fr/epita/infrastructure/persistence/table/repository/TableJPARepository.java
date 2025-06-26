package fr.epita.infrastructure.persistence.table.repository;

import fr.epita.domain.table.model.Table;
import fr.epita.domain.table.port.TableRepository;
import fr.epita.infrastructure.persistence.table.entity.TableJPAEntity;
import fr.epita.infrastructure.persistence.table.utils.TableConverter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

interface SpringDataTableRepository extends JpaRepository<TableJPAEntity, String> {
    List<TableJPAEntity> findAllByGameName(String gameName);
    List<TableJPAEntity> findAllBySeance_Id(String seanceId);
}

@Repository
public class TableJPARepository implements TableRepository {

    private final SpringDataTableRepository springDataTableRepository;

    public TableJPARepository(SpringDataTableRepository springDataTableRepository) {
        this.springDataTableRepository = springDataTableRepository;
    }

    @Override
    public Table save(Table table) {
        TableJPAEntity tableJPAEntity;
        if (table.getId() == null) {
            tableJPAEntity = TableConverter.tableJPAEntityFromDomain(table);
        } else {
            tableJPAEntity = springDataTableRepository.findById(table.getId().toString())
                    .orElseThrow(() -> new EntityNotFoundException("TableJPAEntity not found with id " + table.getId()));
            tableJPAEntity = TableConverter.updateTableJPAEntityFromDomain(table,  tableJPAEntity);
        }
        TableJPAEntity savedTableJPAEntity = springDataTableRepository.save(tableJPAEntity);
        return TableConverter.tableFromTableJPAEntity(savedTableJPAEntity);
    }

    @Override
    public Optional<Table> findById(String id) {
        return springDataTableRepository.findById(id)
                .map(TableConverter::tableFromTableJPAEntity);
    }

    @Override
    public List<Table> findAll() {
        return springDataTableRepository.findAll()
                .stream()
                .map(TableConverter::tableFromTableJPAEntity)
                .toList();
    }

    @Override
    public List<Table> findAllByGameName(String gameName) {
        return springDataTableRepository.findAllByGameName(gameName)
                .stream()
                .map(TableConverter::tableFromTableJPAEntity)
                .toList();
    }

    @Override
    public void delete(Table table) {
        if (table.getId() == null) {
            throw new IllegalArgumentException("Table id cannot be null for deletion");
        }
        springDataTableRepository.deleteById(table.getId().toString());
    }

    @Override
    public Table update(Table table) {
        if (table.getId() == null) {
            throw new IllegalArgumentException("Table id cannot be null for update");
        }
        TableJPAEntity tableJPAEntity = springDataTableRepository.findById(table.getId().toString())
                .orElseThrow(() -> new EntityNotFoundException("TableJPAEntity not found with id " + table.getId()));
        tableJPAEntity = TableConverter.updateTableJPAEntityFromDomain(table,  tableJPAEntity);
        TableJPAEntity updatedTableJPAEntity = springDataTableRepository.save(tableJPAEntity);
        return TableConverter.tableFromTableJPAEntity(updatedTableJPAEntity);
    }

    @Override
    public List<Table> findBySeanceId(String seanceId) {
        return springDataTableRepository.findAllBySeance_Id(seanceId)
                .stream()
                .map(TableConverter::tableFromTableJPAEntity)
                .toList();
    }
}
