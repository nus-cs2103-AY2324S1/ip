package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand class represents a command to list all tasks in the task list.
 *
 * @author selwyn
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand by displaying the list of tasks.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param ui The Ui object handling user interface interactions.
     * @param storage The Storage object handling storage-related operations.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList, false);
    }
}