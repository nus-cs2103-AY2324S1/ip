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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        int size = tasks.getSize();
        for (int i = 1; i <= size; i++) {
            ui.showMessage(i + "." + tasks.getTask(i).toString());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
