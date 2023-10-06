package seedu.duke.exceptions;

/**
 * The InvalidEventException is thrown when a command that creates a event
 * task is not in the correct format.
 */
public class InvalidEventException extends LemonException {
    public InvalidEventException(String message) {
        super(":( OPPS!!! Please add a start and end of the event with /from & /to!");
    }
}
