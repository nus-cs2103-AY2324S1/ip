package helpbuddy.command;

import java.io.IOException;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) throws HelpBuddyException {
        if (taskIndex < 0) {
            throw new HelpBuddyException("Invalid task number.\n");
        }
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, HelpBuddyException {
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
