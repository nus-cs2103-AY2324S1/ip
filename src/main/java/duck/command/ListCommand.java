package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;
import duck.task.TaskList;

/**
 * Represents an executable command which lists all tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) throws DuckException{
        ui.showAllTasks(tasks);
    }
}
