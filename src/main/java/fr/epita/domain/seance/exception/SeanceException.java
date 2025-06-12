package fr.epita.domain.seance.exception;

public class SeanceException extends RuntimeException {
    public SeanceException(String message) {
        super("Seance model : " + message);
    }
}
