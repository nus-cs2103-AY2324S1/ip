package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;

/**
 * Represents a generic command for GrumpyGordon.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param taskList The list of tasks
     * @param storage The storage
     * @return The output string
     */
    public abstract String execute(TaskList taskList, Storage storage);

    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
