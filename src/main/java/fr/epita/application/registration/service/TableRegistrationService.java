package fr.epita.application.registration.service;

import fr.epita.application.player.exception.PlayerNotFoundException;
import fr.epita.application.registration.exception.PlayerAldreadyRegisteredException;
import fr.epita.application.registration.exception.PlayerNotRegisteredException;
import fr.epita.application.registration.exception.TableFullException;
import fr.epita.application.table.exception.TableNotFoundException;
import fr.epita.domain.player.model.Player;
import fr.epita.domain.player.port.PlayerRepository;
import fr.epita.domain.registration.service.TableRegistrationVerificationService;
import fr.epita.domain.table.model.Table;
import fr.epita.domain.table.port.TableRepository;
import fr.epita.presentation.registration.dto.TableRegistrationCreateDto;
import fr.epita.presentation.registration.dto.TableRegistrationDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TableRegistrationService {
    private final PlayerRepository playerRepository;
    private final TableRepository tableRepository;
    private final TableRegistrationVerificationService tableRegistrationVerificationService;

    public TableRegistrationService(PlayerRepository playerRepository, TableRepository tableRepository) {
        this.playerRepository = playerRepository;
        this.tableRepository = tableRepository;
        this.tableRegistrationVerificationService = new TableRegistrationVerificationService();
    }

    @Transactional
    public TableRegistrationDto registerPlayerToTable(TableRegistrationCreateDto tableRegistrationCreateDto) {
        Player player = playerRepository.findById(tableRegistrationCreateDto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + tableRegistrationCreateDto.getPlayerId()));
        Table table = tableRepository.findById(tableRegistrationCreateDto.getTableId())
                .orElseThrow(() -> new TableNotFoundException("Table not found with id: " + tableRegistrationCreateDto.getTableId()));

        if (table.isPlayerRegistered(player)) {
            throw new PlayerAldreadyRegisteredException("player with id: " + tableRegistrationCreateDto.getPlayerId() + " is already registered");
        }
        if (table.isFull()) {
            throw new TableFullException("table with id: " + tableRegistrationCreateDto.getTableId() + " is full");
        }
        tableRegistrationVerificationService.checkTableOverlap(player, table, player.getTables());

        Player updatedPlayer = tableRegistrationVerificationService.checkPlayerFirstSeanceAndMember(player, table);
        Player savedPlayer = playerRepository.save(updatedPlayer);

        table.addPlayer(savedPlayer);
        tableRepository.update(table);

        System.out.println(table.getPlayers().stream().toList().get(0).getId());

        return new TableRegistrationDto(
                player.getId().toString(),
                table.getId().toString(),
                true
        );
    }

    public TableRegistrationDto unregisterPlayerFromTable(TableRegistrationCreateDto tableRegistrationCreateDto) {
        Player player = playerRepository.findById(tableRegistrationCreateDto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + tableRegistrationCreateDto.getPlayerId()));
        Table table = tableRepository.findById(tableRegistrationCreateDto.getTableId())
                .orElseThrow(() -> new TableNotFoundException("Table not found with id: " + tableRegistrationCreateDto.getTableId()));

        if (!table.isPlayerRegistered(player)) {
            throw new PlayerNotRegisteredException("player with id: " + tableRegistrationCreateDto.getPlayerId() + " is not registered");
        }

        table.removePlayer(player);
        tableRepository.update(table);
        playerRepository.update(player);

        return new TableRegistrationDto(
                player.getId().toString(),
                table.getId().toString(),
                false
        );
    }
}
