package fr.epita.domain.seance.model;

import fr.epita.domain.seance.exception.SalleException;

public record Salle(int capacity, double price) {
    public Salle {
        if (capacity <= 0) {
            throw new SalleException("Capacity must be greater than 0");
        }
        if (price <= 0) {
            throw new SalleException("Price must be greater than 0");
        }
    }

    @Override
    public String toString() {
        return "Salle{" +
                "capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}
