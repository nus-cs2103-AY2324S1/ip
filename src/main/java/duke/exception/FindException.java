package duke.exception;

import duke.Ui;

public class FindException extends DukeException {
    public FindException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"find [task_keyword]\""));
    }
}
