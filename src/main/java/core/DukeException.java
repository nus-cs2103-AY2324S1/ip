package core;

/**
 * Represents a custom exception for Duke application.
 * This exception is thrown when Duke-specific conditions are violated.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
