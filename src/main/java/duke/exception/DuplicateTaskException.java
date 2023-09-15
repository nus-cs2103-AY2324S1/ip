package duke.exception;

import duke.message.ErrorMessage;

/**
 * An exception that is thrown when a duplicate task is added.
 */
public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException(String message) {
        super(message + " is already in the task list!");
    }
    public ErrorMessage generateErrorMessage() {
        return new ErrorMessage( getMessage() + " is already in the task list!");
    }
}
