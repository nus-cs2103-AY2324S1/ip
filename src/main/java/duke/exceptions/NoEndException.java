package duke.exceptions;

/**
 * This exception is thrown when no end datetime is provided when attempting
 * to create an Event.
 */
public class NoEndException extends DukeException {
    public NoEndException() {
        super("(・´з`・) Uh oh... please add an end date");
    }
}