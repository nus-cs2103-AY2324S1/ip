package helpbuddy.command;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.ui.Ui;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

import java.io.IOException;

/**
 * The DeleteCommand class deletes a value of type Task to TaskList and calls Ui to print corresponding
 * message upon execution.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new DeleteCommand object with the specified int taskIndex.
     * @param taskIndex the taskIndex of Task to delete.
     * @throws HelpBuddyException if the taskIndex value is out of range (taskIndex < 0).
     */
    public DeleteCommand(int taskIndex) throws HelpBuddyException {
        if (taskIndex < 0) {
            throw new HelpBuddyException("Invalid task number.\n");
        }
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes a Task of taskIndex from TaskList and calls Ui to print corresponding message.
     * @param taskList the tasklist for Task to be deleted.
     * @param ui the ui that prints message.
     * @param storage the storage with saved data in TaskList.
     * @throws HelpBuddyException if taskIndex is out of range (taskIndex > taskList.getSize()).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HelpBuddyException {
        if (this.taskIndex > taskList.getSize()) {
            throw new HelpBuddyException("Invalid task number.\n");
        }
        Task task = taskList.getTask(this.taskIndex - 1);
        taskList.deleteTask(this.taskIndex - 1);
        ui.printDeleteTaskMessage(task, taskList);
    }

    /**
     * Compares the object to the specified object. The result is true if and only if argument is not null and
     * the taskIndex is the same taskIndex as the object.
     * @param obj the object to compare with.
     * @return true if objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof DeleteCommand) {
            DeleteCommand deleteCmd = (DeleteCommand) obj;
            return this.taskIndex == deleteCmd.taskIndex;
        }

        return false;
    }
}
