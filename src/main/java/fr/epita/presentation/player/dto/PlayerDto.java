package fr.epita.presentation.player.dto;

import java.util.UUID;

public class PlayerDto {
    private UUID id;
    private String email;
    private boolean member;
    private boolean firstSeanceUsed;

    public PlayerDto(UUID id, String email, boolean member, boolean firstSeanceUsed) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.firstSeanceUsed = firstSeanceUsed;
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

    public boolean isFirstSeanceUsed() {
        return firstSeanceUsed;
    }

    public void setFirstSeanceUsed(boolean firstSeanceUsed) {
        this.firstSeanceUsed = firstSeanceUsed;
    }
}
