package kevin.commands;

import kevin.exceptions.CommandDetailException;
import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a command in the Duke application.
 * This is an abstract class and serves as a blueprint for various commands in the application.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.unmarkTask(index);
        ui.showUnmark(tasks, index);
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage)
            throws StorageException, CommandDetailException {
        tasks.unmarkTask(index);
        String output = ui.showUnmarkGui(tasks, index);
        storage.save(tasks);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
