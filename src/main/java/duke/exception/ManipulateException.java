package duke.exception;

import duke.Ui;

/**
 * Represents an exception thrown when the user enters an invalid
 * manipulate command keyword.
 */
public class ManipulateException extends DukeException {

    /**
     * Constructs a ManipulateException.
     *
     * @param message The message to be printed when the exception is caught.
     * @param keyword The keyword that the user entered.
     */
    public ManipulateException(String message, String keyword) {
        super(Ui.connectTwoLine(message, String.format(
                "Enter in the form: \"%s [task_number]\" or \"%s all\"", keyword, keyword)));
    }
}
