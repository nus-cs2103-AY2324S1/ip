package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.exceptions.JoeIndexOutOfBoundsException;

/**
 * An abstract class representing a command that can be executed on the task list.
 */
public abstract class Command {
    protected boolean isExit = false;

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     * @throws JoeIndexOutOfBoundsException If there is an invalid index used in the command.
     */
    public abstract String execute(TaskList tasks, Storage storage)
            throws JoeIndexOutOfBoundsException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command; otherwise, false.
     */
    public boolean isExit() {
        return isExit;
    }
}
