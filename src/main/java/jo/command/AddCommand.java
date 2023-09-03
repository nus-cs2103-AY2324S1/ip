package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;
import jo.task.Task;

/**
 * The `AddCommand` class represents a command for adding a task to the task list in the `Jo` application.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs an `AddCommand` object with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {

        this.task = task;
    }

    /**
     * Executes the command, adding the task to the task list, updating storage, and displaying the result.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for loading and saving tasks to a file.
     * @throws JoException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        tasks.addTask(this.task);
        storage.update(tasks);
        ui.modifyListResult(this.task, tasks, true);
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since adding a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Retrieves the task associated with this `AddCommand`.
     *
     * @return The task to be added to the task list.
     */
    public Task getTask() {
        return this.task;
    }


}
