package duke.exception;

/**
 * Represents an exception that occurs when the format for deleting a task is incorrect.
 */
public class DeleteException extends DukeException {
    public DeleteException() {
        super("The format for deleting a task is incorrect. Please use: 'delete [index of task]'");
    }

    public DeleteException(String message) {
        super("The format for deleting a task is incorrect. Please use: 'delete [index of task]'"
                + "\n" + message);
    }
}
