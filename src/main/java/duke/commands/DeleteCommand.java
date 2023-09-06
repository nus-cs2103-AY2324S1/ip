package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.exceptions.StorageException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
