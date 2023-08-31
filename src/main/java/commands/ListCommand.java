package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to display the list of tasks to the user.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to display the list of tasks to the user.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface to display the list of tasks.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print(); // Display the list of tasks through the user interface
    }
}
