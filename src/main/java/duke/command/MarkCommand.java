package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `MarkCommand` class represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {

    private int markIndex;

    /**
     * Constructs a new `MarkCommand` with the specified task index to be marked as done.
     *
     * @param markIndex The index of the task to be marked as done.
     */
    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.mark(markIndex);
        ui.markItem(item.toString());
        storage.writeData(items.getItems());
    }
}
