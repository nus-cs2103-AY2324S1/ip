package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The ListCommand class represents a command to list out all the tasks
 * in the current task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showAllTasks(storage.load());
    }
}
