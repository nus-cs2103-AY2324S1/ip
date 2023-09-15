package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
