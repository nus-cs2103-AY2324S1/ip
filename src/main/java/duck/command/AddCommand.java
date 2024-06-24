package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.task.Task;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which adds a task.
 */
public class AddCommand extends Command {
    private Task newTask;

    /**
     * Creates a new add command.
     * 
     * @param newTask Task to be added.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.add(newTask);
        storage.addTask(newTask);
        return "Got it. I've added this task: \n"
                + newTask + "\n"
                + "Now you have " + tasks.getTaskCount() + " task(s) in the list.";
    }
}
