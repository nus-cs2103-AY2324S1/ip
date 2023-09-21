package avalon.command;

import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to list all tasks in the task list.
 * This command is triggered by user input "list".
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand to display all tasks in the task list.
     *
     * @param taskList The TaskList containing tasks to be listed.
     * @param storage  Not used in this command.
     * @param ui       The Ui instance for displaying the list of tasks.
     * @return A string message indicating the result of the command's execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTasksList(taskList);
        return ui.getOutput();
    }
}
