package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

/**
 * Represents a command to display the list of tasks.
 * Inherits from the Command class.
 */
public class ListCommand extends Command {

    /**
     * Displays the tasks in the list.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @return Response as String.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:\n";
        int size = tasks.getSize();
        for (int taskId = 1; taskId <= size; taskId++) {
            response = response + taskId + "." + tasks.getTask(taskId).toString() + "\n";
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
