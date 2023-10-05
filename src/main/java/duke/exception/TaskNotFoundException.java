package duke.exception;

/**
 * Represents the exceptions for the unfounded .
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs a TaskNotFoundException with a specified message.
     *
     * @param message A message describing the error.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
