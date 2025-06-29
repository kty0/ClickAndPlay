package fr.epita.infrastructure.persistence.player.entity;

import fr.epita.infrastructure.persistence.table.entity.TableJPAEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
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
    @Column
    private boolean member;
    @Column
    private boolean firstSeanceUsed;
    @ManyToMany(mappedBy = "players", fetch = FetchType.EAGER)
    private Set<TableJPAEntity> tables = new HashSet<>();

    public PlayerJPAEntity() {}

    public PlayerJPAEntity(String id, String email, boolean member, boolean firstSeanceUsed) {
        this.id = id;
        this.email = email;
        this.member = member;
        this.firstSeanceUsed = firstSeanceUsed;
    }

    public void addTable(TableJPAEntity table) {
        this.tables.add(table);
        table.getPlayers().add(this);
    }

    public void removeTable(TableJPAEntity table) {
        this.tables.remove(table);
        table.getPlayers().remove(this);
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
    public boolean isFirstSeanceUsed() {
        return firstSeanceUsed;
    }
    public void setFirstSeanceUsed(boolean firstSeanceUsed) {
        this.firstSeanceUsed = firstSeanceUsed;
    }
    public Set<TableJPAEntity> getTables() {
        return tables;
    }
    public void setTables(Set<TableJPAEntity> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerJPAEntity that = (PlayerJPAEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
