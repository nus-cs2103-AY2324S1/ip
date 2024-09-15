package kevin.commands;

import kevin.exceptions.CommandDetailException;
import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a delete command in the Duke application.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.deleteTask(index);
        ui.showDelete(tasks, index);
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.deleteTask(index);
        String output = ui.showDeleteGui(tasks, index);
        storage.save(tasks);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
