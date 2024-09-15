package kevin.commands;

import kevin.exceptions.CommandDetailException;
import kevin.exceptions.StorageException;
import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents a mark command in the Duke application.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.markTask(index);
        ui.showMark(tasks, index);
        storage.save(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws StorageException, CommandDetailException {
        tasks.markTask(index);
        String output = ui.showMarkGui(tasks, index);
        storage.save(tasks);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
