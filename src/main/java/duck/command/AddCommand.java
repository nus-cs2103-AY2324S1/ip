package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.Task;
import duck.task.TaskList;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.add(newTask);
        ui.showAddTaskMessage(newTask, tasks.getTaskCount());
        storage.addTask(newTask);
    }
}
