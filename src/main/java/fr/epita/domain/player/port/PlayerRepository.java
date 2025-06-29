package fr.epita.domain.player.port;

import fr.epita.domain.player.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> findById(String id);
    List<Player> findAll();
    void delete(Player player);
    Player update(Player player);
}
