package duke.exception;

/**
 * A class that represents all exceptions thrown when user wants to add tasks but did not input description
 */
public class EmptyDescriptionException extends MissingInformationException {
    /**
     * Constructor for exception
     */
    public EmptyDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}
