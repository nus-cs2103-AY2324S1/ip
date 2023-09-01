package duke.exception;

/**
 * A class that represents all exceptions thrown when user is trying to access tasks in the program, 
 * but provided index is out of bounds
 */
public class IndexOutOfBoundsException extends DukeException {
    /**
     * Constructor for exception
     */
    public IndexOutOfBoundsException() {
        super("☹ OOPS!!! Index provided is invalid.");
    }
}
