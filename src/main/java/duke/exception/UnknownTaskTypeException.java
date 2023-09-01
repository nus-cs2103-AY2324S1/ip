package duke.exception;

/**
 * A class that represents all exceptions thrown by the program when user inputs a command that
 * is not expected
 */
public class UnknownTaskTypeException extends DukeException {
    /**
     * Constructor for exception
     */
    public UnknownTaskTypeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
