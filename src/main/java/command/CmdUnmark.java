package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as not done in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdUnmark extends Command {
    private int index;

    /**
     * Constructs a CmdUnmark object with the index of the task to be marked as not
     * done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public CmdUnmark(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done, displays the unmarked task,
     * and updates the storage.
     *
     * @param taskList The task list in which the task should be marked as not done.
     * @param ui       The user interface for displaying feedback to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markNotDone(index);
        ui.print("Meow! I've marked this task as not done yet:");
        ui.print(taskList.getTask(index).toString());
        Storage.writeToFile(taskList);
    }

}
