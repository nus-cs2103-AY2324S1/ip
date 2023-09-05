package duke.exceptions;

/**
 * This exception is thrown when the deadline command is of the incorrect format.
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("(・´з`・) Uh oh... make sure your deadline has a description and date");
    }
}