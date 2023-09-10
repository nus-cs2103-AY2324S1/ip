package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.Task;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which deletes a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new delete command.
     * 
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        Task tmpTask = tasks.getTask(index);
        tasks.delete(index);
        storage.deleteTask(index);
        return "Noted. I've removed this task: \n"
                + tmpTask + "\n"
                + "Now you have " + tasks.getTaskCount() + " task(s) in the list.";
    }
}
