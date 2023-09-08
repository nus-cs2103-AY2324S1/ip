package duke.exceptions;

/**
 * This is the parent exception of all exceptions created in this duke program.
 */
public class DukeException extends Exception {

    public DukeException() {
        super("(・´з`・) Uh oh...smt went wrong");
    }
    public DukeException(String message) {
        super(message);
    }
}
