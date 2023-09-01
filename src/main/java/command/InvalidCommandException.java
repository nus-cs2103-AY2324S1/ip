package command;

import duke.DukeException;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
