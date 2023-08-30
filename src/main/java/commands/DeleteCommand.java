package commands;

import exceptions.StorageException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        tasks.deleteTask(index);
        ui.showDelete(tasks, index);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
