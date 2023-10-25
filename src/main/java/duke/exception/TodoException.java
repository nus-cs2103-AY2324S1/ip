package duke.exception;

import duke.util.Ui;

/**
 * Represents an exception thrown when the user enters an invalid todo command.
 */
public class TodoException extends DukeException {

    /**
     * Constructs a TodoException.
     *
     * @param message The message to be printed when the exception is caught.
     */
    public TodoException(String message) {
        super(Ui.connectLines(message,
                "Enter in the form: \"todo [task_description]\""));
    }
}
