package fr.epita.application.table.service;

import fr.epita.application.seance.exception.SeanceNotFoundException;
import fr.epita.application.table.exception.FreeTableDeletionException;
import fr.epita.application.table.exception.TableNotFoundException;
import fr.epita.application.table.utils.TableConverter;
import fr.epita.domain.common.port.EmailSender;
import fr.epita.domain.player.model.Player;
import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.seance.port.SeanceRepository;
import fr.epita.domain.table.model.Table;
import fr.epita.domain.table.port.TableRepository;
import fr.epita.domain.table.service.TableVerificationService;
import fr.epita.presentation.table.dto.TableCreateDto;
import fr.epita.presentation.table.dto.TableDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void createFreeTable(Seance seance) {
        LocalDateTime startDateTime = seance.getDate();
        int durationInMinutes = seance.getDurationInHours() * 60;
        int estimatedDurationInMinutes = durationInMinutes - 15;
        int estimatedDurationInHours = estimatedDurationInMinutes / 60;

        Table freeTable = new Table(
                seance,
                "Libre",
                seance.getSalle().capacity(),
                startDateTime,
                estimatedDurationInHours,
                true
        );

        tableRepository.save(freeTable);
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

        if (table.isFree()) {
            throw new FreeTableDeletionException("free table cannot be deleted");
        }

        List<String> emails = new ArrayList<>();
        for (Player player : table.getPlayers()) {
            emails.add(player.getEmail());
        }
        emailSender.sendEmailToMultipleRecipients(
                emails,
                "Table deleted",
                "The table with id " +  id + " has been deleted"
        );

        tableRepository.delete(table);
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
