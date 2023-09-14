package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to list all tasks in the list.
 */
public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showTaskList(tasks);
    }
}
