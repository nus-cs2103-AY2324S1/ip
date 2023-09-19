package duke.exceptions;

/**
 * This exception is thrown when start datetime is after end datetime when creating
 * an Event.
 */
public class InvalidStartEndException extends DukeException {
    public InvalidStartEndException() {
        super("(・´з`・) Uh oh... start must be after end!");
    }
}
