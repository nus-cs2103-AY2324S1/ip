package duke.exceptions;

/**
 * Custom exception class for handling invalid tasks.
 */
public class InvalidTaskException extends MyBotExceptions {
    /**
     * Constructs an InvalidTaskException instance with a default error message.
     */
    public InvalidTaskException() {
        super("☹ OOPS! This is an invalid task :(");
    }
}