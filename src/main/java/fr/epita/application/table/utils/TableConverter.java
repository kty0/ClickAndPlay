package fr.epita.application.table.utils;

import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.table.model.Table;
import fr.epita.presentation.table.dto.TableCreateDto;
import fr.epita.presentation.table.dto.TableDto;

public class TableConverter {
    public static Table newTableFromTableDTO(TableCreateDto tableCreateDto, Seance seance) {
        return new Table(
                seance,
                tableCreateDto.getGameName(),
                tableCreateDto.getMaxPlayers(),
                tableCreateDto.getStartDateTime(),
                tableCreateDto.getEstimatedDurationInHours(),
                false
        );
    }

    public static Table tableFromTableDTO(TableDto tableDto, Seance seance) {
        return new Table(
                tableDto.getId(),
                seance,
                tableDto.getGameName(),
                tableDto.getMaxPlayers(),
                tableDto.getStartDateTime(),
                tableDto.getEstimatedDurationInHours(),
                false
        );
    }

    public static TableDto tableDTOFromTable(Table table) {
        return new TableDto(
                table.getId(),
                table.getSeance().getId(),
                table.getGameName(),
                table.getMaxPlayers(),
                table.getStartDateTime(),
                table.getEstimatedDurationInHours(),
                table.isFree()
        );
    }
}
