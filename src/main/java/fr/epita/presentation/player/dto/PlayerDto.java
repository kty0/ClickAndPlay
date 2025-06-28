package fr.epita.presentation.player.dto;

import fr.epita.domain.player.model.Role;

import java.util.Set;
import java.util.UUID;

public class PlayerDto {
    private UUID id;
    private String email;
    private Set<Role> roles;
    private boolean member;
    private boolean firstSeance;

    public PlayerDto(UUID id, String email, Set<Role> roles, boolean member, boolean firstSeance) {
        this.id = id;
        this.email = email;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean getMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public boolean getFirstSeance() {
        return firstSeance;
    }

    public void setFirstSeance(boolean firstSeance) {
        this.firstSeance = firstSeance;
    }
}
