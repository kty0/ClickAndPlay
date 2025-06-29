package fr.epita.application.registration.exception;

public class PlayerAldreadyRegisteredException extends RuntimeException {
    public PlayerAldreadyRegisteredException(String message) {
        super("Player already registered: " + message);
    }
}
