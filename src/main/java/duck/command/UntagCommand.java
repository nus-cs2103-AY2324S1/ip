package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which untags a task.
 */
public class UntagCommand extends Command {
    private int index;

    /**
     * Creates a new untag command.
     * 
     * @param index Index of task to be untagged.
     */
    public UntagCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.untag(index);
        storage.updateTasks(tasks);
        return "Nice! I've removed the tag on this task: \n"
                + tasks.getTask(index);
    }
}