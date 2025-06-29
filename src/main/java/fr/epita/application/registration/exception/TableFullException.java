package fr.epita.application.registration.exception;

public class TableFullException extends RuntimeException {
    public TableFullException(String message) {
        super("Table is already full: " + message);
    }
}
