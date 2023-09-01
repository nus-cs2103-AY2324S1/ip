package duke.commands;

import duke.DukeException;

/**
 * An exception to be thrown when an error occurs while parsing or executing a command.
 */
public class CommandException extends DukeException {
    public CommandException(String message) {
        super(message);
    }
}
