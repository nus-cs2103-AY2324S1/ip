package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which lists all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        if (tasks.getTaskCount() == 0) {
            return "You have no tasks yet.";
        }
        return "Here are the tasks in your list: \n"
                + tasks;
    }
}
