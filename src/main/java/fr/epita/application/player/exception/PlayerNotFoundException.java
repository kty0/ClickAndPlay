package fr.epita.application.player.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String message) {
        super("Player not found : " + message);
    }
}
