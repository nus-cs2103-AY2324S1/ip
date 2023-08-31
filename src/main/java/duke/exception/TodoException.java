package duke.exception;

import duke.exception.DukeException;

public class TodoException extends DukeException {
    public TodoException() {
        super("The description of a todo cannot be empty.");
    }
}
