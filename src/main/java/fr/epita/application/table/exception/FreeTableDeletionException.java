package fr.epita.application.table.exception;

public class FreeTableDeletionException extends RuntimeException {
    public FreeTableDeletionException(String message) {
        super("Free table deletion : " + message);
    }
}
