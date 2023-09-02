package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.unmark(index);
        ui.showUnmarkTaskMessage(tasks.getTask(index));
        storage.updateTasks(tasks);
    }
}
