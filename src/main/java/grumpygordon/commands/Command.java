package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a generic command for GrumpyGordon.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param taskList The list of tasks
     * @param ui The user interface
     * @param storage The storage
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command
     */
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
