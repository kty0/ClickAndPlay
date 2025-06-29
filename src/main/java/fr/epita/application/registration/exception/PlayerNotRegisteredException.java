package fr.epita.application.registration.exception;

public class PlayerNotRegisteredException extends RuntimeException {
    public PlayerNotRegisteredException(String message) {
        super("Player not registered: " + message);
    }
}
