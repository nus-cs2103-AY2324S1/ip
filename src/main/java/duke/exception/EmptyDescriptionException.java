package duke.exception;

/**
 * Represents an exception that is thrown when a task description is empty.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructs a new EmptyDescriptionException with a default error message.
     */
    public EmptyDescriptionException() {
        super("The description of a task cannot be empty.");
    }
}
