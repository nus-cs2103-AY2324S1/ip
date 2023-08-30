package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(TaskList tasks, Ui ui, Storage storage, int index) {
        super(tasks, ui, storage);
        this.index = index;
    }

    @Override
    public void execute() {
        tasks.deleteTask(index);
        ui.showDeleteTask(tasks, index);
        storage.save(tasks);
    }
}
