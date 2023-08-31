package helpbuddy.command;

import java.io.IOException;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) throws HelpBuddyException {
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
        taskList.deleteTask(this.taskIndex - 1);
        ui.printDeleteTaskMessage(task, taskList);
    }

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
