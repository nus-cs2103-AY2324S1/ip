package commands;

import exceptions.StorageException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        ui.showUnmark(tasks, index);
        tasks.unmarkTask(index);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
