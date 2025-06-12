package fr.epita.application.seance.exception;

public class SeanceNotFoundException extends RuntimeException {
    public SeanceNotFoundException(String message) {
        super("Seance not found : " + message);
    }
}
