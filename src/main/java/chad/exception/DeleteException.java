package chad.exception;

/**
 * Represents an error that occurred during the initialisation of a delete command.
 */
public class DeleteException extends CommandException {
    public DeleteException() {
        super("delete TASK_INDEX");
    }
}
