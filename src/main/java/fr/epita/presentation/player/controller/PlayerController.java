package fr.epita.presentation.player.controller;

import fr.epita.application.player.exception.PlayerNotFoundException;
import fr.epita.application.player.service.PlayerService;
import fr.epita.domain.player.exception.PlayerException;
import fr.epita.presentation.player.dto.PlayerCreateDto;
import fr.epita.presentation.player.dto.PlayerDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerCreateDto playerCreateDto) {
        PlayerDto playerDto = playerService.createPlayer(playerCreateDto);
        return ResponseEntity.ok(playerDto);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerDto> playerDTOs = playerService.getAllPlayers();
        return ResponseEntity.ok(playerDTOs);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        PlayerDto playerDto = playerService.getPlayer(playerId);
        return ResponseEntity.ok(playerDto);
    }

    @DeleteMapping
    public ResponseEntity<PlayerDto> deletePlayer(@RequestParam String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        playerService.deletePlayer(playerId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{playerId}/cotisation")
    public ResponseEntity<PlayerDto> becomeMember(@PathVariable String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        PlayerDto updatedPlayer = playerService.becomeMember(playerId);
        return ResponseEntity.ok(updatedPlayer);
    }

    @ExceptionHandler({PlayerException.class})
    public ResponseEntity<String> handlePlayerException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({PlayerNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<String> handlePlayerNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
