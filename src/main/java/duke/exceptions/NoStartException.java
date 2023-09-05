package duke.exceptions;

/**
 * This exception is thrown when no start datetime is provided when attempting
 * to create an Event.
 */
public class NoStartException extends DukeException {
    public NoStartException() {
        super("(・´з`・) Uh oh... please add a start date");
    }
}
