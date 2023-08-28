package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;

/**
 * Represents a command issued by the user to list all tasks in the list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(tasks);
    }
}
