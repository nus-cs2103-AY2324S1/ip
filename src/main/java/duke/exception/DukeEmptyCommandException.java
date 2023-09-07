package duke.exception;

/**
 * An exception occurs when the command is empty.
 */
public class DukeEmptyCommandException extends DukeException {
    /**
     * Constructor for the exception.
     */
    public DukeEmptyCommandException() {

        super("A command is needed for the program to excute.");
    }
}
