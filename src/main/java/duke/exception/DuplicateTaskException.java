package duke.exception;

import duke.message.ErrorMessage;

/**
 * An exception that is thrown when a duplicate task is added.
 */
public class DuplicateTaskException extends DukeException {
    public DuplicateTaskException(String message) {
        super(message);
    }
    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + " is already in the task list!");
    }
}
