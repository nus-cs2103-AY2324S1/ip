package duke.exception;

/**
 * Represents an exception that is thrown when a todo task has an empty description.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructs a new EmptyDescriptionException with a default error message.
     */
    public EmptyDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}
