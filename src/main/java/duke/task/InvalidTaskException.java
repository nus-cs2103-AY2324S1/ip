package duke.task;

/**
 * An exception for invalid tasks.
 * Used when task operations are invalid.
 */
public class InvalidTaskException extends RuntimeException {

    public InvalidTaskException(String message) {
        super(message);
    }

}
