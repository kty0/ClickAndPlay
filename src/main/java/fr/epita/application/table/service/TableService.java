package fr.epita.application.table.service;

import fr.epita.application.seance.exception.SeanceNotFoundException;
import fr.epita.application.table.exception.TableNotFoundException;
import fr.epita.application.table.utils.TableConverter;
import fr.epita.domain.common.port.EmailSender;
import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.seance.port.SeanceRepository;
import fr.epita.domain.table.model.Table;
import fr.epita.domain.table.port.TableRepository;
import fr.epita.domain.table.service.TableVerificationService;
import fr.epita.presentation.table.dto.TableCreateDto;
import fr.epita.presentation.table.dto.TableDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    private final TableRepository tableRepository;
    private final SeanceRepository seanceRepository;
    private final TableVerificationService tableVerificationService;
    private final EmailSender emailSender;

    public TableService(TableRepository tableRepository, SeanceRepository seanceRepository, EmailSender emailSender) {
        this.tableRepository = tableRepository;
        this.seanceRepository = seanceRepository;
        this.tableVerificationService = new TableVerificationService();
        this.emailSender = emailSender;
    }

    @Transactional
    public TableDto createTable(final TableCreateDto tableCreateDto) {
        Seance seance = seanceRepository.findById(tableCreateDto.getSeanceId().toString())
                .orElseThrow(() -> new SeanceNotFoundException("Seance not found with ID: " + tableCreateDto.getSeanceId()));
        Table table = TableConverter.newTableFromTableDTO(tableCreateDto, seance);

        List<Table> existingTables = new ArrayList<>(tableRepository.findBySeanceId(seance.getId().toString()));
        existingTables.add(table);
        tableVerificationService.verifyTable(existingTables, seance);

        Table savedTable = tableRepository.save(table);
        return TableConverter.tableDTOFromTable(savedTable);
    }

    @Transactional
    public TableDto updateTable(final TableDto tableDto) {
        Seance seance = seanceRepository.findById(tableDto.getSeanceId().toString())
                .orElseThrow(() -> new SeanceNotFoundException("Seance not found with ID: " + tableDto.getSeanceId()));
        Table table = TableConverter.tableFromTableDTO(tableDto, seance);

        List<Table> existingTables = tableRepository.findBySeanceId(seance.getId().toString());
        existingTables.add(table);
        tableVerificationService.verifyTable(existingTables, seance);

        Table savedTable = tableRepository.save(table);
        return TableConverter.tableDTOFromTable(savedTable);
    }

    @Transactional
    public void deleteTable(final String id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Table not found with ID: " + id));

        tableRepository.delete(table);
        emailSender.sendEmail(
                "All users registered to the table",
                "Table deleted",
                "The table with id " +  id + " has been deleted"
        );
    }

    public List<TableDto> getAllTables() {
        List<Table> tables = tableRepository.findAll();
        List<TableDto> tableDTOs = new ArrayList<>();
        for (Table table : tables) {
            tableDTOs.add(TableConverter.tableDTOFromTable(table));
        }

        return tableDTOs;
    }

    public TableDto getTable(final String id) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new TableNotFoundException("Table not found with ID: " + id));
        return TableConverter.tableDTOFromTable(table);
    }
}
