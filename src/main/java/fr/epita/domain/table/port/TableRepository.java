package fr.epita.domain.table.port;

import fr.epita.domain.table.model.Table;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TableRepository {
    Table save(Table table);
    Optional<Table> findById(String id);
    List<Table> findAll();
    List<Table> findAllByGameName(String gameName);
    void delete(Table table);
    Table update(Table table);
    List<Table> findBySeanceId(String seanceId);
}
