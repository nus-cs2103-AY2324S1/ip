package duke.exception;

import duke.util.Ui;

/**
 * Represents an exception thrown when the find command is invalid.
 */
public class FindException extends DukeException {

    /**
     * Constructs a <code>FindException</code> object.
     *
     * @param message The error message to be printed when the exception is caught.
     */
    public FindException(String message) {
        super(Ui.connectLines(message,
                "Enter in the form: \"find [task_keyword]\""));
    }
}
