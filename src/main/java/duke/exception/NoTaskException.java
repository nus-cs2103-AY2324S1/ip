package duke.exception;
/**
 * Exception when there is no task given.
 */
public class NoTaskException extends DukeException {
    /**
     * Constructor of a NoTaskException.
     */
    public NoTaskException() {
        super("Error! Cannot add an empty task!");
    }
}
