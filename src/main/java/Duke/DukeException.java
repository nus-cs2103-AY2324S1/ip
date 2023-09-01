package Duke;

/**
 * The DukeException class represents an exception that can be thrown by the Duke program.
 * It extends the Exception class and provides a custom message to display to the user.
 */
public class DukeException extends Exception {
    String message;

    /**
     * Constructs a new DukeException object with the given message.
     * @param message the message to display to the user
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the message of the exception.
     * @return a string representing the message of the exception
     */
    public String getMessage() {
        return this.message;
    }
}
