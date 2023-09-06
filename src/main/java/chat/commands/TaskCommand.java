package chat.commands;

import chat.tasks.TaskList;
import chat.utils.Storage;

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
    public String execute(TaskList tasklist, Storage storage) {
        return "";
    }
}
