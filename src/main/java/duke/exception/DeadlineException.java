package duke.exception;

import duke.Ui;

public class DeadlineException extends DukeException {

    public DeadlineException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"deadline [task_description] /by {d/M/yyyy HH:mm}\""));
    }
}
