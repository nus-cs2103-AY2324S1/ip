package exception;

/**
 * Represents an Exception where a task is missing when expected.
 */
public class MissingTaskException extends DukeException {
    public MissingTaskException(String command) {
        super(command + " needs a task after it...");
    }
}
