package fluke.exceptions;

/**
 * This exception should be thrown when an empty description is given for a task.
 */
public class EmptyDescriptionException extends FlukeException {
    private static final String ERROR_MESSAGE = "I cannot create a task without a description!";

    /**
     * Constructs an EmptyDescriptionException.
     */
    public EmptyDescriptionException() {
        super(ERROR_MESSAGE);
    }
}
