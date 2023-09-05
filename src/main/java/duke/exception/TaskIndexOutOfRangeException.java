package Duke.exception;

import Duke.message.ErrorMessage;

/**
 * An exception that is thrown when a task index is out of range.
 */
public class TaskIndexOutOfRangeException extends DukeException {
    public TaskIndexOutOfRangeException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + 1 + " is out of range of the task list.");
    }
}
