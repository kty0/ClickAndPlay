package fr.epita.domain.player.exception;

public class PlayerException extends RuntimeException {
    public PlayerException(String message) {
        super("Player model : " + message);
    }
}
