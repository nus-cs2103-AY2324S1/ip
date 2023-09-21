package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a mark command.
 */
public class MarkException extends CommandException {
    public MarkException() {
        super("mark TASK_INDEX");
    }
}
