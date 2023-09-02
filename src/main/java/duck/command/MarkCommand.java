package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

/**
 * Represents an executable command which marks a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Creates a new mark command.
     * 
     * @param index Index of task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.mark(index);
        ui.showMarkTaskMessage(tasks.getTask(index));
        storage.updateTasks(tasks);
    }
}
