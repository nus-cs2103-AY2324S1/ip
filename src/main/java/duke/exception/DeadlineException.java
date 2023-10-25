package duke.exception;

import duke.util.Ui;

/**
 * Represents a checked exception thrown when the deadline command is not in the correct format.
 */
public class DeadlineException extends DukeException {

    /**
     * Constructs a DeadlineException.
     *
     * @param message The error message to be printed when the exception is caught.
     */
    public DeadlineException(String message) {
        super(Ui.connectLines(message,
                "Enter in the form: \"deadline [task_description] /by {d/M/yyyy HH:mm}\""));
    }
}
