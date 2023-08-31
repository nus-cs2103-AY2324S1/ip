package helpbuddy.command;

import helpbuddy.storage.Storage;
import helpbuddy.task.Deadline;
import helpbuddy.ui.Ui;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;

import java.io.IOException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.task);
        ui.printAddTaskMessage(this.task, taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof AddCommand) {
            AddCommand addCmd = (AddCommand) obj;
            return this.task.equals(addCmd.task);
        }

        return false;
    }
}
