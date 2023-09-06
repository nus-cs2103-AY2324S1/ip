package seedu.duke;

/**
 * Encapsulates the exception where the name of the task inputted is not specified.
 */
public class InvalidDescriptionException extends DukeException {
    /**
     * Creates an InvalidDescriptionException.
     *
     * @param taskType The type of task given by the user.
     */
    public InvalidDescriptionException(String taskType) {
        super("\u2639 OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}
