package duke.exceptions;

/**
 * This exception is thrown when the event command is of the incorrect format.
 */
public class InvalidEventException extends DukeException {

    public InvalidEventException() {
        super("(・´з`・) Uh oh... improper event format!");
    }
}
