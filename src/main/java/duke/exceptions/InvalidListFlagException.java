package duke.exceptions;

/**
 * Exception is thrown when the list command is of the incorrect format.
 */
public class InvalidListFlagException extends DukeException {

    public InvalidListFlagException() {
        super("(・´з`・) Uh oh... please ensure format is 'list today/week/fort'");
    }
}
