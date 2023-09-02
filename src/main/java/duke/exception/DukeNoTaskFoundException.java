package duke.exception;

/**
 * Represents an error when Duke encounters invalid index for TaskList operations.
 */
public class DukeNoTaskFoundException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "No such task found :-(\n";
    }
}
