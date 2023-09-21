package duke.command;

import duke.exception.DukeException;

/**
 * Represents a command that interpret the user input string and execute the requirement.
 */
public interface Command {
    public String execute(String input) throws DukeException;
}
