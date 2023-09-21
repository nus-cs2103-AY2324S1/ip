package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;

/**
 * Represents an abstract command that can be executed by Jarvis.
 * This class serves as the base class for all specific commands
 * that Jarvis can execute.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the specific implementation of this command.
     *
     * @param tasks The list of tasks currently managed by Jarvis.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to read or save tasks to disk.
     * @throws JarvisException If there is any error during the command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException;

    /**
     * Checks if this command should cause Jarvis to exit.
     *
     * @return true if Jarvis should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}