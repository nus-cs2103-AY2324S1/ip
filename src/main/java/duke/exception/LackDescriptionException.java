package duke.exception;

/**
 * An exception thrown when the description of the task is not provided.
 */
public class LackDescriptionException extends DukeException {
    /**
     * Creates a duke.exception.LackDescriptionException instance.
     *
     * @param s Message of the exception.
     */
    public LackDescriptionException(String s) {
        super("The description of "+ s + " should not be empty");
    }
}
