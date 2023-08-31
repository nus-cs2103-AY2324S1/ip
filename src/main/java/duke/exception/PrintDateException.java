package duke.exception;

import duke.Ui;

/**
 * Represents an exception thrown when the user enters an invalid print_date command.
 */
public class PrintDateException extends DukeException {

    /**
     * Constructs a PrintDateException.
     *
     * @param message The message to be printed when the exception is caught.
     */
    public PrintDateException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"print_date [deadline/event] /on {d/M/yyyy}\""));
    }
}
