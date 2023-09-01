package duke.exception;

/**
 * The InvalidDateException class represents an exception that is thrown when invalid dates
 * are input to a command in the Duke application.
 * It is a subclass of DukeException and provides a specific error message.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructs a new InvalidDateException with an error message.
     */
    public InvalidDateException() {
        super("☹ OOPS!!! I'm sorry, but your date is in the incorrect format! " +
                "please key it in yyyy-mm-dd format! :-(");
    }
}
