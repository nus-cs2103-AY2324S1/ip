package duke.exception;

/**
 * The duke.exception.EmptyDescriptionException Class extends duke.exception.DukeException and
 * is used to denote when a command has a missing description
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("The description cannot be left blank");
    }
}
