package fr.epita.presentation.player.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PlayerCreateDto {
    private String email;
    @Schema(example = "false")
    private boolean member;

    public PlayerCreateDto(String email, boolean member) {
        this.email = email;
        this.member = member;
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
}
