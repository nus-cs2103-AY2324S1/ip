package command;

import duke.DukeException;

/**
 * Signals the input from the user is not recognised as a valid command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
