package duke.command.task;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `DeleteCommand` class represents a command to delete a task from the task list.
 */
public class DeleteTaskCommand extends Command {

    private int deleteNumber;

    /**
     * Constructs a new `DeleteCommand` with the specified task number to be deleted.
     *
     * @param deleteNumber The task number to be deleted.
     */
    public DeleteTaskCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        Task item = items.delete(deleteNumber);
        storage.writeData(items);
        return ui.deleteItem(item.toString(), items.getCount());
    }
}
