package fr.epita.domain.player.model;

import fr.epita.domain.player.exception.PlayerException;

import java.util.Set;
import java.util.UUID;

public class Player {
    private UUID id;
    private String email;
    private Set<Role> roles;
    private boolean member; // cotisant
    private boolean firstSeance;

    public Player(UUID id, String email, Set<Role> roles, boolean member, boolean firstSeance) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.member = member;
        this.firstSeance = firstSeance;

        if (id == null) {
            throw new PlayerException("Player id must not be null");
        }

        validate();
    }

    public Player(String email, Set<Role> roles, boolean member, boolean firstSeance) {
        this.email = email;
        this.roles = roles;
        this.member = member;
        this.firstSeance = firstSeance;
    }

    private void validate() {
        if (email == null || email.isBlank()) {
            throw new PlayerException("Email must not be null");
        }
        if (roles == null || roles.isEmpty()) {
            throw new PlayerException("Roles must not be empty");
        }
    }

    public void becomeMember() {
        if (member) {
            throw new PlayerException("player with id " + id + " is already member");
        }

        member = true;
        System.out.println("Player " + id + " is now member");
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean getMember() {
        return member;
    }

    public boolean getFirstSeance() {
        return firstSeance;
    }
}
