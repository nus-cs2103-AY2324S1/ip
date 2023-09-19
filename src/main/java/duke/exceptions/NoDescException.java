package duke.exceptions;

/**
 * This exception is thrown when no description is provided when attempting to
 * create any Task object.
 */
public class NoDescException extends DukeException {
    public NoDescException() {
        super("(・´з`・) Uh oh... please add a description");
    }
}
