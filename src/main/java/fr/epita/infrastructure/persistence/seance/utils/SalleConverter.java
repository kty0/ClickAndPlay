package fr.epita.infrastructure.persistence.seance.utils;

import fr.epita.domain.seance.model.Salle;
import fr.epita.infrastructure.persistence.seance.entity.SalleJPAEntity;

public class SalleConverter {

    public static SalleJPAEntity SalleJPAEntityfromDomain(Salle salle) {
        return new SalleJPAEntity(salle.capacity(), salle.price());
    }
    
    public static Salle salleFromSalleJPAEntity(SalleJPAEntity salleJPAEntity) {
        return new Salle(salleJPAEntity.getCapacity(), salleJPAEntity.getPrice());
    }
}
