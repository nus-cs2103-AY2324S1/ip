package duke.exception;

/**
 * The DuplicateTaskException class represents an exception that is thrown when duplicate Tasks
 * are added in the Duke application.
 * It is a subclass of DukeException and provides a specific error message.
 */
public class DuplicateTaskException extends DukeException {

    /**
     * Constructs a new InvalidArgumentException with a custom error message.
     *
     */
    public DuplicateTaskException() {
        super("☹ OOPS!!! I'm sorry, but this task already exists");
    }
}
