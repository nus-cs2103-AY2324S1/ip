package martin.commands;

import martin.exceptions.MartinException;

public interface Command {
    /**
     * Executes the specific command.
     */
    String execute() throws MartinException;
}