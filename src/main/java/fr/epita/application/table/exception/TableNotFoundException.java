package fr.epita.application.table.exception;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(String message) {
        super("Table not found: " + message);
    }
}
