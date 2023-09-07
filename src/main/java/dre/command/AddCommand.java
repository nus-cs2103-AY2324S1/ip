package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.task.Task;
import dre.task.TaskList;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
    }
}