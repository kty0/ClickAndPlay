package fr.epita.domain.player.model;

import fr.epita.domain.player.exception.PlayerException;
import fr.epita.domain.table.model.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Player {
    private UUID id;
    private String email;
    private boolean member; // cotisant
    private boolean firstSeanceUsed;
    private Set<Table> tables = new HashSet<>();

    public Player(UUID id, String email, boolean member, boolean firstSeanceUsed) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.firstSeanceUsed = firstSeanceUsed;

        if (id == null) {
            throw new PlayerException("Player id must not be null");
        }

        validate();
    }

    public Player(String email, boolean member, boolean firstSeanceUsed) {
        this.email = email;
        this.member = member;
        this.firstSeanceUsed = firstSeanceUsed;
    }

    private void validate() {
        if (email == null || email.isBlank()) {
            throw new PlayerException("Email must not be null");
        }
    }

    public void becomeMember() {
        if (member) {
            throw new PlayerException("player with id " + id + " is already member");
        }

        member = true;
        System.out.println("Player " + id + " is now member");
    }

    public void useFirstSeance() {
        firstSeanceUsed = true;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMember() {
        return member;
    }

    public boolean isFirstSeanceUsed() {
        return firstSeanceUsed;
    }

    public Set<Table> getTables() {
        return tables;
    }

    public void setTables(Set<Table> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
