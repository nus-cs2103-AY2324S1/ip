package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

/**
 * Represent a command that can be executed to produce effects on a task list, ui and storage.
 */
public abstract class Command {
    /**
     * Executes the command on a task list, ui and storage.
     *
     * @param tasks a TaskList instance for the command to execute on
     * @param ui a Ui instance for the command to execute on
     * @param storage a Storage instance for the command to execute on
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is an instance of ExitCommand.
     * Returns false otherwise.
     *
     * @return a boolean value indicating if it is an ExitCommand instance
     */
    public boolean isExit() {
        return false;
    }
}
