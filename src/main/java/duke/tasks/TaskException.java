package duke.tasks;

import duke.DukeException;

/**
 * An exception to be thrown when a task related error occurs.
 */
public class TaskException extends DukeException {
    public TaskException(String message) {
        super(message);
    }
}
