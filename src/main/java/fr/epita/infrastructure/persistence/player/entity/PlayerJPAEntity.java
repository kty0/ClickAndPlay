package fr.epita.infrastructure.persistence.player.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "players")
public class PlayerJPAEntity {
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column
    private String email;
    @Column
    private boolean member;
    @Column
    private boolean firstSeance;

    public PlayerJPAEntity() {}

    public PlayerJPAEntity(String id, String email, boolean member, boolean firstSeance) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.firstSeance = firstSeance;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
