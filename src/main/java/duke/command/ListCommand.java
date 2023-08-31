package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `ListCommand` class represents a command to list the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ui.list(items.getItems());
    }
}
