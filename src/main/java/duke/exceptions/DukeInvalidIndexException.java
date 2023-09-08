package duke.exceptions;

/**
 * Represents an exception where the index of the task is invalid.
 *
 * @author Andrew Daniel Janong
 */
public class DukeInvalidIndexException extends DukeException {
    public DukeInvalidIndexException(String message) {
        super("OOPS!!! You currently have " + message + " task(s). Please enter a valid index.");
    }
}
