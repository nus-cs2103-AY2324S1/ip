package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(this.taskNumber);
        task.unmarkDone();
        ui.showUnmarkText(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
