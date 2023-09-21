package duke.parse.command;

import duke.Duke;

/**
 * Represents a parsed command from the user.
 * The base class for a command, to be implemented by other classes.
 */
public interface Command {
    /**
     * Executes the command.
     * @param bot The bot to execute this command on.
     * @return Whether the execution allows the program to continue,
     *     true if it can, false means the program must exit.
     */
    boolean execute(Duke bot);
}
