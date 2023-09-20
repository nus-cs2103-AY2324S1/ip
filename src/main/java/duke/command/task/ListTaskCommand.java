package duke.command.task;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `ListCommand` class represents a command to list the tasks in the task list.
 */
public class ListTaskCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        return ui.list(items.getArrayList());
    }
}
