package duke.exception;

/**
 * Represents an error that occurred during the initialisation of a unmark command.
 */
public class UnmarkException extends CommandException {
    public UnmarkException() {
        super("unmark TASK_INDEX");
    }
}
