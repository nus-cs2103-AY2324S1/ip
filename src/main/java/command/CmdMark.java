package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdMark extends Command {
    private int index;

    /**
     * Constructs a CmdMark object with the index of the task to be marked as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public CmdMark(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as done, displays the marked task,
     * and updates the storage.
     *
     * @param taskList The task list in which the task should be marked as done.
     * @param ui       The user interface for displaying feedback to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markDone(index);
        ui.print("Meow! I've marked this task as done:");
        ui.print(taskList.getTask(index).toString());
        Storage.writeToFile(taskList);
    }

}
