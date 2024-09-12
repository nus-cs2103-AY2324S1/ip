package max.commands;


import max.exception.MaxException;
import max.storage.Storage;
import max.tasks.TaskList;
import max.ui.Ui;

/**
 * Represents commands by user.
 */
public abstract class Command {
    /**
     * Public constructor for Command.
     */
    public Command() {

    }

    /**
     * Executes given command.
     *
     * @param tasks Task list
     * @param ui UI
     * @param storage Storage
     * @throws MaxException If command is invalid
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException;

    /**
     * Checks if command is an exit command.
     *
     * @return True if exit command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
