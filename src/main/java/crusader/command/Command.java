package crusader.command;

import crusader.Storage;
import crusader.TaskList;
import crusader.exception.CrusaderException;

/**
 * A command, representing a singular action in the bot
 */
public abstract class Command {
    /**
     * Generates a new command.
     */
    public Command() {
        // empty
    }

    /**
     * Runs the command.
     *
     * @param taskList Task list to update or access as required
     * @throws CrusaderException On malformed user input
     */
    public abstract String execute(TaskList taskList, Storage storage) throws CrusaderException;

    /**
     * Checks whether the command is used to terminate the bot.
     * @return Whether the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
