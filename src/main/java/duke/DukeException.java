package duke;

/**
 * Represents exceptions specific to the Duke application.
 * <p>
 * This class extends the standard {@code Exception} class and is used to handle
 * errors and exceptional scenarios that are specific to the Duke application's
 * operations.
 * </p>
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     *
     * @param message The detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }
}
