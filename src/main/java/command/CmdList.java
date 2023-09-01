package command;

import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdList extends Command {

    /**
     * Executes the command to list all tasks and their indices in the task list.
     *
     * @param taskList The task list to retrieve tasks from.
     * @param ui       The user interface for displaying the list of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            Task task = taskList.getTask(i - 1);
            ui.print(String.format("%d. %s", i, task.toString()));
        }
    }

}
