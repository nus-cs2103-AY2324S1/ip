package duke.exception;

import duke.exception.DukeException;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
