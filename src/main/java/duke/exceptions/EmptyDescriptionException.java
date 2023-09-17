package duke.exceptions;

/**
 * Represents an exception that is thrown when a task is provided with an empty description.
 */
public class EmptyDescriptionException extends Exception {

    /**
     * Constructs a new EmptyDescriptionException with a detailed error message.
     *
     * @param message The detailed error message associated with the exception.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

