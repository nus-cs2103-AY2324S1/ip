package commands;

import exceptions.StorageException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException {
        ui.showMark(tasks, index);
        tasks.markTask(index);
        storage.save(tasks);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
