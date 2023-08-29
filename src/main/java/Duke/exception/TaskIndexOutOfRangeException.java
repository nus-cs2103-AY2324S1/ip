package Duke.exception;

import Duke.message.ErrorMessage;
public class TaskIndexOutOfRangeException extends DukeException {
    public TaskIndexOutOfRangeException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + 1 + " is out of range of the task list.");
    }
}
