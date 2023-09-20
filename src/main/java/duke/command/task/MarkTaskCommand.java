package duke.command.task;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `MarkCommand` class represents a command to mark a task as done in the task list.
 */
public class MarkTaskCommand extends Command {

    private int markIndex;

    /**
     * Constructs a new `MarkCommand` with the specified task index to be marked as done.
     *
     * @param markIndex The index of the task to be marked as done.
     */
    public MarkTaskCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.mark(markIndex);
        storage.writeData(items);
        return ui.markItem(item.toString());
    }
}
