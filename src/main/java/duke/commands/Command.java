package duke.commands;

import duke.exceptions.DukeException;

public abstract class Command {

    /**
     * Executes command.
     * @param parsedInput the parsed command from the user.
     * @return String response to the command.
     */
    public abstract String execute(String[] parsedInput, String message)
            throws DukeException;

}
