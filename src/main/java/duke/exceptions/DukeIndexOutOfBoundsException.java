package duke.exceptions;

/**
 * IndexOutOfBoundsException for Duke.
 */
public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String action) {
        super("OOPS!!! Index of task to " + action + " is out of bounds");
    }
}
