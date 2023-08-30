package duke.exception;

import duke.Ui;

public class PrintDateException extends DukeException {

    public PrintDateException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"print_date [deadline/event] /on {d/M/yyyy}\""));
    }
}
