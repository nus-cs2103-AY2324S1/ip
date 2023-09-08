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
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:\n";
        int size = tasks.getSize();
        for (int i = 1; i <= size; i++) {
            response = response + i + "." + tasks.getTask(i).toString() + "\n";
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
