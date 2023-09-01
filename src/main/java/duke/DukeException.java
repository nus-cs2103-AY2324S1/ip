package duke;

/**
 * Represents exceptions specific to the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }
}
