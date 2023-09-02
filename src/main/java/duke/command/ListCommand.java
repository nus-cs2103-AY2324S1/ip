package duke.command;

import duke.Command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * The ListCommand is responsible for displaying the entire list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     * This method displays the list of tasks stored in the TaskList object using the Ui object.
     *
     * @param taskList The TaskList object that stores the list of tasks.
     * @param ui       The Ui object responsible for user interface interactions.
     * @param storage  The Storage object responsible for reading and writing data to a file (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayTaskList(taskList);
    }
}