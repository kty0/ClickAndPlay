package fr.epita.presentation.registration.controller;

import fr.epita.application.player.exception.PlayerNotFoundException;
import fr.epita.application.registration.exception.PlayerAldreadyRegisteredException;
import fr.epita.application.registration.exception.PlayerNotRegisteredException;
import fr.epita.application.registration.exception.TableFullException;
import fr.epita.application.registration.service.TableRegistrationService;
import fr.epita.application.table.exception.TableNotFoundException;
import fr.epita.domain.registration.exception.RegistrationException;
import fr.epita.presentation.registration.dto.TableRegistrationCreateDto;
import fr.epita.presentation.registration.dto.TableRegistrationDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/table-registrations")
public class TableRegistrationController {
    private final TableRegistrationService tableRegistrationService;

    public TableRegistrationController(TableRegistrationService tableRegistrationService) {
        this.tableRegistrationService = tableRegistrationService;
    }

    @PostMapping
    public ResponseEntity<TableRegistrationDto> registerPlayerToTable(@RequestBody TableRegistrationCreateDto tableRegistrationCreateDto) {
        TableRegistrationDto registrationDto = tableRegistrationService.registerPlayerToTable(tableRegistrationCreateDto);
        return ResponseEntity.ok(registrationDto);
    }

    @PatchMapping("/unregister")
    public ResponseEntity<TableRegistrationDto> unregisterPlayerFromTable(@RequestBody TableRegistrationCreateDto tableRegistrationCreateDto) {
        TableRegistrationDto registrationDto = tableRegistrationService.unregisterPlayerFromTable(tableRegistrationCreateDto);
        return ResponseEntity.ok(registrationDto);
    }

    @ExceptionHandler({
            PlayerAldreadyRegisteredException.class,
            TableFullException.class,
            PlayerNotRegisteredException.class,
            RegistrationException.class
    })
    public ResponseEntity<String> handleBadRequestExceptions(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({
            PlayerNotFoundException.class,
            TableNotFoundException.class,
            EntityNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
