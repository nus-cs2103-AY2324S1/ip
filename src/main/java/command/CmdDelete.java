package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdDelete extends Command {
    private int index;

    /**
     * Constructs a CmdDelete object with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public CmdDelete(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list, displays
     * the deleted task, updates the task list size, and writes changes to
     * the storage.
     *
     * @param taskList The task list from which the task should be deleted.
     * @param ui       The user interface for displaying feedback to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task deleted = taskList.deleteTask(index);
        ui.print("Meow. I've removed this task:");
        ui.print(deleted.toString());
        ui.print(String.format("Now you have %d tasks in the list.", taskList.size()));
        Storage.writeToFile(taskList);
    }

}
