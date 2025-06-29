package fr.epita.infrastructure.config;

import fr.epita.application.player.service.PlayerService;
import fr.epita.application.seance.service.SeanceService;
import fr.epita.application.table.service.TableService;
import fr.epita.presentation.player.dto.PlayerCreateDto;
import fr.epita.presentation.seance.dto.SeanceCreateDto;
import fr.epita.presentation.seance.dto.SeanceDto;
import fr.epita.presentation.table.dto.TableCreateDto;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataTest implements CommandLineRunner {
    private final PlayerService playerService;
    private final TableService tableService;
    private final SeanceService seanceService;

    public DataTest(PlayerService playerService, TableService tableService, SeanceService seanceService) {
        this.playerService = playerService;
        this.tableService = tableService;
        this.seanceService = seanceService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1).withHour(19).withMinute(0).withSecond(0).withNano(0);

        SeanceCreateDto seance1 = new SeanceCreateDto("Le mercredi des loups-garous", tomorrow, 4, 20, 15);
        SeanceCreateDto seance2 = new SeanceCreateDto("Uno en folie", tomorrow.withHour(20), 3,  15, 10);
        seanceService.createSeance(seance1);
        SeanceDto savedSeance2 = seanceService.createSeance(seance2);

        PlayerCreateDto player1 = new PlayerCreateDto("antoine.racha@epita.fr", false);
        PlayerCreateDto player2 = new PlayerCreateDto("k2@epita.fr", true);
        PlayerCreateDto player3 = new PlayerCreateDto("edgar.tourneur@epita.fr", true);
        PlayerCreateDto player4 = new PlayerCreateDto("hedelin.ropital@epita.fr", false);
        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        playerService.createPlayer(player3);
        playerService.createPlayer(player4);

        TableCreateDto table1 = new TableCreateDto(savedSeance2.getId(), "Uno", 2, tomorrow.withHour(20), 1);
        tableService.createTable(table1);
    }
}
