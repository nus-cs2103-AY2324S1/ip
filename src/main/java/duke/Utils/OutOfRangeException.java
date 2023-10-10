package duke.utils;

/**
 * The OutOfRangeException class represents an exception that is thrown when
 * a user provides an input number that is out of range of the current tasks.
 */
public class OutOfRangeException extends DukeException {
    /**
     * Constructs a new OutOfRangeException with a default error message.
     */
    protected OutOfRangeException() {
        super("I'm sorry, but your input number is out of range of the current tasks");
    }
}
