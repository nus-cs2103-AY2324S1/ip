package duke.exception;

/**
 * The duke.exception.MissingIndexException Class extends duke.exception.DukeException and is
 * used to denote that there is a missing parameter or index
 */
public class MissingIndexException extends DukeException {
    public MissingIndexException() {
        super("The index does not exist");
    }
}
