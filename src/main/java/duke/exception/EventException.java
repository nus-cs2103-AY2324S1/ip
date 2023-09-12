package duke.exception;

import duke.util.Ui;

/**
 * Represents an exception thrown when the user enters an invalid event command.
 */
public class EventException extends DukeException {

    /**
     * Constructs an EventException.
     *
     * @param message The message to be printed when the exception is caught.
     */
    public EventException(String message) {
        super(Ui.connectLines(message,
                "Enter in the form: \"event [task_description] /from {d/M/yyyy HH:mm} /to {d/M/yyyy HH:mm}\""));
    }
}
