package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.mark(index);
        storage.updateTasks(tasks);
        return "Nice! I've marked this task as done: \n"
                + tasks.getTask(index);
    }
}
