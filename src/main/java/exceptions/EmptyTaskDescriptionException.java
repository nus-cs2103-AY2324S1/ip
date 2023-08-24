package exceptions;

public class EmptyTaskDescriptionException extends Exception {
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}