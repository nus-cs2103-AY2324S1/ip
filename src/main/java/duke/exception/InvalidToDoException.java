package duke.exception;
/**
 * The Exception for invalid todo command
 */
public class InvalidToDoException extends Exception {
    // Constructor

    /**
     * The constructor of InvalidToDoException
     */
    public InvalidToDoException() {
        super("☹ OOPS!!! I'm sorry, but the input ToDo is invalid :-(");
    }
}
