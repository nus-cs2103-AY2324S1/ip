package duke.exception;

/**
 * The InvalidArgumentException class represents an exception that is thrown when invalid arguments
 * are provided to a command in the Duke application.
 * It is a subclass of DukeException and provides a specific error message.
 */
public class InvalidArgumentException extends DukeException {

    /**
     * Constructs a new InvalidArgumentException with a custom error message.
     *
     * @param s The name of the command for which the arguments are invalid.
     */
    public InvalidArgumentException(String s) {
        super("☹ OOPS!!! I'm sorry, but arguments to " + s + " cannot be empty");
    }
}