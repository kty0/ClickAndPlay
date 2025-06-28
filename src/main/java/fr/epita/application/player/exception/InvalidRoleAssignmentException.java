package fr.epita.application.player.exception;

public class InvalidRoleAssignmentException extends RuntimeException {
    public InvalidRoleAssignmentException(String message) {
        super("Invalid role assignment : " + message);
    }
}
