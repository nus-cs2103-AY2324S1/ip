package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which tags a task.
 */
public class TagCommand extends Command {
    private int index;
    private String tag;

    /**
     * Creates a new tag command.
     * 
     * @param index Index of task to be tagged.
     */
    public TagCommand(int index, String tag) {
        this.index = index - 1;
        this.tag = tag;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.tag(index, tag);
        storage.updateTasks(tasks);
        return "Nice! I've tagged this task: \n"
                + tasks.getTask(index);
    }
}