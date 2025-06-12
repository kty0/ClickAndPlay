package fr.epita.domain.seance.exception;

public class SalleException extends RuntimeException {
    public SalleException(String message) {
        super("salle model : " + message);
    }
}
