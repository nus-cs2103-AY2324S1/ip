package duke.exceptions;

/**
 * This exception is thrown when no/more than one keyword is provided
 * when user enters the find command.
 */
public class InvalidFindTaskException extends DukeException {
    public InvalidFindTaskException() {
        super("(・´з`・) Uh oh...please provide exactly one keyword");
    }
}
