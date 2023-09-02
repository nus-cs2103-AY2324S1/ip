package crusader.command;

import crusader.exception.CrusaderException;
import crusader.TaskList;
import crusader.Ui;

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
     * @param ui UI to generate output if required
     * @param taskList Task list to update or access as required
     * @throws CrusaderException On malformed user input
     */
    public abstract void execute(Ui ui, TaskList taskList) throws CrusaderException;

    /**
     * Checks whether the command is used to terminate the bot.
     * @return Whether the command is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
