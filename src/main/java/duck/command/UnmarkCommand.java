package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which unmarks a task as done.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Creates a new unmark command.
     * 
     * @param index Index of task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.unmark(index);
        storage.updateTasks(tasks);
        return "OK, I've unmarked this task: \n"
                + tasks.getTask(index);
    }
}
