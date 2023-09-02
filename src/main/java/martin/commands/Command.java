package martin.commands;

import martin.exceptions.MartinException;

public interface Command {
    /**
     * Executes the specific command.
     */
    void execute() throws MartinException;
}