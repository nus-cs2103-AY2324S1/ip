/**
 * Custom Exception class that extends DukeException. It is thrown when the description of the task
 * created is empty.
 *
 */
public class InvalidDescriptionException extends DukeException {
    /**
     * Constructs new InvalidDescriptionException with specified error messages.
     *
     * @param message The type of task created with an invalid description.
     */
    InvalidDescriptionException(String message) {
        super("☹ OOPS!!! The description of a message " + message + " cannot be empty");
    }
}
