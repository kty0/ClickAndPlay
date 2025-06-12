package fr.epita.infrastructure.persistence.seance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SalleJPAEntity {
    @Column
    private int capacity;
    @Column
    private double price;

    public SalleJPAEntity() {
    }
    public SalleJPAEntity(int capacity, double price) {
        this.capacity = capacity;
        this.price = price;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
