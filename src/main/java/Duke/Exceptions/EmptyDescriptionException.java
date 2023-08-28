package Duke.Exceptions;

public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super("EmptyDescriptionException: " + message + "\n");
    }
}