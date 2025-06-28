package fr.epita.presentation.table.controller;

import fr.epita.application.table.exception.TableNotFoundException;
import fr.epita.application.table.service.TableService;
import fr.epita.domain.table.exception.TableException;
import fr.epita.presentation.table.dto.TableCreateDto;
import fr.epita.presentation.table.dto.TableDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<TableDto> createTable(@RequestBody TableCreateDto tableCreateDto) {
        TableDto createdTable = tableService.createTable(tableCreateDto);
        return ResponseEntity.ok(createdTable);
    }

    @GetMapping
    public ResponseEntity<List<TableDto>> getAllTables() {
        List<TableDto> tables = tableService.getAllTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{tableId}")
    public ResponseEntity<TableDto> getTable(@PathVariable String tableId) {
        if (tableId == null || tableId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        TableDto table = tableService.getTable(tableId);
        return ResponseEntity.ok(table);
    }

    @DeleteMapping
    public ResponseEntity<TableDto> deleteTable(@RequestParam String tableId) {
        if (tableId == null || tableId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        tableService.deleteTable(tableId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({TableException.class})
    public ResponseEntity<String> handleTableException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({TableNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleTableNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
