package fr.epita.presentation.player.dto;

import java.util.UUID;

public class PlayerDto {
    private UUID id;
    private String email;
    private boolean member;
    private boolean firstSeance;

    public PlayerDto(UUID id, String email, boolean member, boolean firstSeance) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.firstSeance = firstSeance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean isFirstSeance() {
        return firstSeance;
    }

    public void setFirstSeance(boolean firstSeance) {
        this.firstSeance = firstSeance;
    }
}
