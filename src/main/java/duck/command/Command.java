package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command. Abstract class that cannot be instantiated.
 */
public abstract class Command {
    /**
     * Executes the command.
     * 
     * @param tasks   Task list.
     * @param ui      User interface.
     * @param storage Storage.
     * @return String The reply to the user.
     * @throws DuckException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException;

    /**
     * Returns true only if the command is an exit command.
     * 
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    };
}
