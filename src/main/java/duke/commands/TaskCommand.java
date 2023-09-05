package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * @author juzzztinsoong
 */
public abstract class TaskCommand extends Command {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for TaskCommand.
     * @param description the description of the todo.
     * @param isDone true if task is done, false otherwise.
     */
    public TaskCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public void load(TaskList tasklist) {
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
    }
}
