package helpbuddy.command;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.ui.Ui;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

import java.io.IOException;

/**
 * The UnmarkCommand class marks a value of type Task in TaskList as not done and calls Ui to print corresponding
 * message upon execution.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new UnmarkCommand object with the specified int taskIndex.
     * @param taskIndex the taskIndex of Task to unmark as not done.
     * @throws HelpBuddyException if the taskIndex value is out of range (taskIndex < 0).
     */
    public UnmarkCommand(int taskIndex) throws HelpBuddyException {
        if (taskIndex < 0) {
            throw new HelpBuddyException("Invalid task number.\n");
        }
        this.taskIndex = taskIndex;
    }

    /**
     * Unmarks Task of taskIndex in TaskList as not done and calls Ui to print corresponding message.
     * @param taskList the tasklist for Task to be unmarked.
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
        if (task.getStatusIcon() == " ") {
            throw new HelpBuddyException("Task is not marked as done.\n");
        }
        task.updateDone();
        ui.printUnmarkMessage(task);
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

        if (obj instanceof UnmarkCommand) {
            UnmarkCommand unmarkCmd = (UnmarkCommand) obj;
            return this.taskIndex == unmarkCmd.taskIndex;
        }

        return false;
    }
}
