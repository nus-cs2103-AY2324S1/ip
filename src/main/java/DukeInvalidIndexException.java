/**
 * Represents an exception where the input of the mark/unmark command is invalid.
 *
 * @author Andrew Daniel Janong
 */
public class DukeInvalidIndexException extends DukeException {
    public DukeInvalidIndexException(String message) {
        super("OOPS!!! You currently have " + message + " task(s). Please enter a valid index.");
    }
}
