package fr.epita.application.seance.service;

import fr.epita.application.seance.exception.SeanceNotFoundException;
import fr.epita.application.seance.utils.SeanceConverter;
import fr.epita.domain.seance.model.Seance;
import fr.epita.domain.common.port.EmailSender;
import fr.epita.domain.seance.port.SeanceRepository;
import fr.epita.domain.seance.service.SeanceVerificationService;
import fr.epita.presentation.seance.dto.SeanceCreateDto;
import fr.epita.presentation.seance.dto.SeanceDto;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final SeanceVerificationService seanceVerificationService;
    private final EmailSender emailSender;

    public SeanceService(SeanceRepository seanceRepository, EmailSender emailSender) {
        this.seanceRepository = seanceRepository;
        this.seanceVerificationService = new SeanceVerificationService();
        this.emailSender = emailSender;
    }

    @Transactional
    public SeanceDto createSeance(SeanceCreateDto seanceDto) {
        Seance seance = SeanceConverter.newSeanceFromSeanceDTO(seanceDto);

        List<Seance> seances = seanceRepository.findAllByName(seanceDto.getName());
        seanceVerificationService.checkSeanceOverlap(seance,seances);
        Seance savedSeance = seanceRepository.save(seance);

        return SeanceConverter.seanceDtoFromSeance(savedSeance);
    }

    @Transactional
    public SeanceDto updateSeance(SeanceDto seanceDto) {
        Seance seance = SeanceConverter.seanceFromSeanceDTO(seanceDto);

        List<Seance> seances = seanceRepository.findAllByName(seanceDto.getName());
        seanceVerificationService.checkSeanceOverlap(seance,seances);
        Seance savedSeance = seanceRepository.update(seance);

        return SeanceConverter.seanceDtoFromSeance(savedSeance);
    }

    @Transactional
    public void deleteSeance(String id) {
        Seance seance = seanceRepository.findById(id).orElse(null);
        if( seance != null ) {
            seanceRepository.delete(seance);
            emailSender.sendEmail( // a modifier pour supprimer pour tous les inscrits
                "All users registered to the seance",
                "Seance Deleted",
                "The seance with id " + id + " has been successfully deleted."
                );
        } else {
            throw new SeanceNotFoundException("the seance with id " + id + " does not exist");
        }
    }

    public List<SeanceDto> getAllSeances() {
        List<Seance> seances = seanceRepository.findAll();
        List<SeanceDto> seancesDto = new ArrayList<>();
        for (Seance seance : seances) {
            seancesDto.add(SeanceConverter.seanceDtoFromSeance(seance));
        }
        return seancesDto;
    }

    public SeanceDto getSeance(String id) {
        Seance seance = seanceRepository.findById(id).orElse(null);
        if (seance == null) {
            throw new SeanceNotFoundException("the seance with id " + id + " does not exist");
        }
        return SeanceConverter.seanceDtoFromSeance(seance);
    }
}
