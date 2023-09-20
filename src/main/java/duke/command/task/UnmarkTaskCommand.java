package duke.command.task;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `UnmarkCommand` class represents a command to unmark a task as not done in the task list.
 */
public class UnmarkTaskCommand extends Command {

    private int unmarkIndex;

    /**
     * Constructs a new `UnmarkCommand` with the specified task index to be unmarked as not done.
     *
     * @param unmarkIndex The index of the task to be unmarked as not done.
     */
    public UnmarkTaskCommand(int unmarkIndex) {
        this.unmarkIndex = unmarkIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.unmark(unmarkIndex);
        storage.writeData(items);
        return ui.unmarkItem(item.toString());
    }
}
