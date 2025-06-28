package fr.epita.presentation.player.dto;

import fr.epita.domain.player.model.Role;

import java.util.Set;

public class PlayerCreateDto {
    private String email;
    private Set<Role> roles;

    public PlayerCreateDto(String email, Set<Role> roles) {
        this.email = email;
        this.roles = roles;
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
}
