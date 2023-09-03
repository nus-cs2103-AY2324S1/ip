package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `ListCommand` class represents a command for listing tasks in the `Jo` application.
 */
public class ListCommand extends Command {

    /**
     * Executes the command, displaying the list of tasks to the user.
     *
     * @param tasks   The `TaskList` containing tasks to be listed.
     * @param ui      The user interface for displaying the list.
     * @param storage The storage object for loading and saving tasks to a file (unused in this command).
     * @throws JoException If an error occurs during the execution of the command (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.printList(tasks);
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since listing tasks does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
