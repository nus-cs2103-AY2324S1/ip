package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `Command` class is an abstract base class for all command objects in the `Jo` application.
 */
public abstract class Command {

    /**
     * Executes the command, performing specific actions on the task list, user interface, and storage.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for loading and saving tasks to a file.
     * @throws JoException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JoException;

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `true` if the command exits the application, `false` otherwise.
     */
    public abstract boolean isExit();
}
