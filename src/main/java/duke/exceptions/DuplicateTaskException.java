package duke.exceptions;

/**
 * Represents an exception when there are duplicate tasks.
 */
public class DuplicateTaskException extends IllegalArgumentException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
