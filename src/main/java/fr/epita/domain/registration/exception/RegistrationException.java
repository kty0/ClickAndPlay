package fr.epita.domain.registration.exception;

public class RegistrationException extends RuntimeException {
    public RegistrationException(String message) {
        super("Registration exception: " + message);
    }
}
