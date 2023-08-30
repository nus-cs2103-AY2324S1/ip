package duke.exception;

import duke.Ui;

public class ManipulateException extends DukeException{

    public ManipulateException(String message, String command) {
        super(Ui.connectTwoLine(message, String.format(
                "Enter in the form: \"%s [task_number]\" or \"%s all\"", command, command)));
    }
}
