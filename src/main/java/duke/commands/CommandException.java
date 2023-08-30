package duke.commands;

import duke.DukeException;

public class CommandException extends DukeException {
    public CommandException(String message) {
        super(message);
    }
}
