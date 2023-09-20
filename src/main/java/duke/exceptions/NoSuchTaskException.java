package duke.exceptions;

/**
 * Custom exception class for handling cases where a specified task is not found.
 */
public class NoSuchTaskException extends MyBotExceptions {
    /**
     * Constructs a NoSuchTaskException instance with a default error message.
     */
    public NoSuchTaskException() {
        super("☹ Oops! Task not found:(");
    }
}
