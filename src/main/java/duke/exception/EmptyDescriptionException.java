package duke.exception;

import duke.exception.DukeException;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}
