package exceptions;

import exceptions.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
