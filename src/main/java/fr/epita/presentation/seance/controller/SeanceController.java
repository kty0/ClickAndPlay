package fr.epita.presentation.seance.controller;

import fr.epita.application.seance.exception.SeanceNotFoundException;
import fr.epita.application.seance.service.SeanceService;
import fr.epita.domain.seance.exception.SalleException;
import fr.epita.domain.seance.exception.SeanceException;
import fr.epita.presentation.seance.dto.SeanceCreateDto;
import fr.epita.presentation.seance.dto.SeanceDto;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/seances")
public class SeanceController {
    private final SeanceService seanceService;
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @PostMapping()
    public ResponseEntity<SeanceDto> createSeance(@RequestBody SeanceCreateDto seanceCreateDto) {
        SeanceDto seanceCreated = seanceService.createSeance(seanceCreateDto);
        return ResponseEntity.ok(seanceCreated);
    }

    @GetMapping()
    public ResponseEntity<List<SeanceDto>> getAllSeances() {
        List<SeanceDto> seances = seanceService.getAllSeances();
        return ResponseEntity.ok(seances);
    }

    @GetMapping("/{seanceId}")
    public ResponseEntity<SeanceDto> getSeanceById(@PathVariable String seanceId) {
        if (seanceId == null || seanceId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        SeanceDto seance = seanceService.getSeance(seanceId);
        return ResponseEntity.ok(seance);
    }

    @DeleteMapping()
    public ResponseEntity<SeanceDto> deleteSeance(@RequestParam String seanceId) {
        if (seanceId == null || seanceId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        seanceService.deleteSeance(seanceId);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({SalleException.class, SeanceException.class})
    public ResponseEntity<String> handleExceptions(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }    

    @ExceptionHandler({SeanceNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handleSeanceNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
