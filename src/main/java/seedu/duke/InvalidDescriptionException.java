package seedu.duke;

/**
 * Encapsulates the exception where the name of the task inputted is not specified.
 */
public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException(String taskType) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                "____________________________________________________________");
    }
}
