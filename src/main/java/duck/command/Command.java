package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;
import duck.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     * 
     * @param tasks   Task list.
     * @param ui      User interface.
     * @param storage Storage.
     * @throws DuckException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui,Storage storage) throws DuckException;

    /**
     * Returns true only if the command is an exit command.
     * 
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    };
}
