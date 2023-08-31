package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `DeleteCommand` class represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private int deleteNumber;

    /**
     * Constructs a new `DeleteCommand` with the specified task number to be deleted.
     *
     * @param deleteNumber The task number to be deleted.
     */
    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.delete(deleteNumber);
        ui.deleteItem(item.toString(), items.getCount());
        storage.writeData(items.getItems());
    }
}
