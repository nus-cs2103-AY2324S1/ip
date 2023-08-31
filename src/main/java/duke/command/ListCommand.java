package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by printing the list of all tasks to the user interface.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTaskList(tasks.getAllTasks(), "Here are your entries:");
    }
}
