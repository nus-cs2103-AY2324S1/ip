package command;

import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;


public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAddText(task, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
