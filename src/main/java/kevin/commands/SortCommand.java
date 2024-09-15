package kevin.commands;

import kevin.exceptions.CommandDetailException;
import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a sort command in the Duke application.
 * Sort the deadline/event start time in ascending order.
 */
public class SortCommand extends Command {
    public SortCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.sort();
        ui.showSort();
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.sort();
        storage.save(tasks);
        return ui.showSortGui();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
