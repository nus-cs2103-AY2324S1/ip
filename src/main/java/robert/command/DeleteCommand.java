package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.task.Task;

public class DeleteCommand extends Command {

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showMessage("Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.");
    }
}
