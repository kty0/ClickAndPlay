package fr.epita.infrastructure.persistence.player.entity;

import fr.epita.domain.player.model.Role;
import jakarta.persistence.*;

import java.util.Set;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_roles", joinColumns = @JoinColumn(name = "player_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;
    @Column
    private boolean member;
    @Column
    private boolean firstSeance;

    public PlayerJPAEntity() {}

    public PlayerJPAEntity(String id, String email, Set<Role> roles, boolean member, boolean firstSeance) {
        this.id = id;
        this.email = email;
        this.roles = roles;
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
