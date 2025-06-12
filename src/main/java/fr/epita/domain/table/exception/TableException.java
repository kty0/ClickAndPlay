package fr.epita.domain.table.exception;

public class TableException extends RuntimeException {
    public TableException(String message) {
        super("Table model : " + message);
    }
}
