package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

/**
 * Represents an abstract command that can be executed.
 * All specific commands should inherit from this class.
 */
public abstract class Command {

    /**
     * Executes the specific command's operation.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     * @throws DreException If there's an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DreException;

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false by default; should be overridden by commands that cause exit.
     */
    public boolean isExit() {
        return false;
    }
}
