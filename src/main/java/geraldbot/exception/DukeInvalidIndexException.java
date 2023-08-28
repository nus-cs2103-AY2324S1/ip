package geraldbot.exception;

/**
 * Represents an exception thrown when an invalid index is provided by the user for a task.
 */
public class DukeInvalidIndexException extends DukeException {

    /**
     * Constructs a DukeInvalidIndexException with a custom error message indicating the invalid index.
     *
     * @param size The size of the task list.
     */
    public DukeInvalidIndexException(Integer size) {
        super("☹ OOPS!!! I'm sorry, but index is invalid! There are " + size + " tasks in the list! :-(");
    }
}
