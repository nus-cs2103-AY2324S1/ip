package duke.exceptions;

public class DukeIndexOutOfBoundsException extends DukeException {
    public DukeIndexOutOfBoundsException(String action) {
        super("OOPS!!! Index of task to be " + action + " is out of bounds");
    }
}
