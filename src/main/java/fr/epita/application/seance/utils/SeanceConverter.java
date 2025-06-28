package fr.epita.application.seance.utils;

import fr.epita.domain.seance.model.Salle;
import fr.epita.domain.seance.model.Seance;
import fr.epita.presentation.seance.dto.SeanceCreateDto;
import fr.epita.presentation.seance.dto.SeanceDto;

public class SeanceConverter {
    public static Seance newSeanceFromSeanceDTO(SeanceCreateDto seanceDto) {
        Salle salle = new Salle(seanceDto.getSalleCapacity(), seanceDto.getSallePrice());
        return new Seance(
            seanceDto.getName(),
            seanceDto.getDate(),
            seanceDto.getDurationInHours(),
            salle
        );
    }

    public static Seance seanceFromSeanceDTO(SeanceDto seanceDto) {
        Salle salle = new Salle(seanceDto.getSalleCapacity(), seanceDto.getSallePrice());
        return new Seance(
                seanceDto.getId(),
                seanceDto.getName(),
                seanceDto.getDate(),
                seanceDto.getDurationInHours(),
                salle
        );
    }

    public static SeanceDto seanceDTOFromSeance(Seance seance) {
        return new SeanceDto(
                seance.getId(),
                seance.getName(),
                seance.getDate(),
                seance.getDurationInHours(),
                seance.getSalle().capacity(),
                seance.getSalle().price()
        );
    }
}
