package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task task = tasks.get(taskNumber);
            tasks.delete(taskNumber);
            ui.showDeleteText(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
