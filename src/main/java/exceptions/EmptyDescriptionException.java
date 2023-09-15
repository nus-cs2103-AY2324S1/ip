package exceptions;

import exceptions.DukeException;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description cannot be empty.");
    }
}
