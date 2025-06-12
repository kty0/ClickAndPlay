package fr.epita.presentation.seance.dto;

import java.time.LocalDateTime;

public class SeanceCreateDto {
    private String name;
    private LocalDateTime date;
    private int durationInHours;
    private int salleCapacity;
    private double sallePrice;

    public SeanceCreateDto(String name, LocalDateTime date, int durationInHours, int salleCapacity, double sallePrice) {
        this.name = name;
        this.date = date;
        this.durationInHours = durationInHours;
        this.salleCapacity = salleCapacity;
        this.sallePrice = sallePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public int getSalleCapacity() {
        return salleCapacity;
    }

    public void setSalleCapacity(int salleCapacity) {
        this.salleCapacity = salleCapacity;
    }

    public double getSallePrice() {
        return sallePrice;
    }

    public void setSallePrice(double sallePrice) {
        this.sallePrice = sallePrice;
    }

}
