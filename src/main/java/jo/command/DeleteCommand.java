package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;
import jo.task.Task;

/**
 * The `DeleteCommand` class represents a command for deleting a task from the task list in the `Jo` application.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a `DeleteCommand` object with the specified index of the task to be deleted.
     *
     * @param index The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command, deleting the specified task from the task list and updating storage.
     *
     * @param tasks   The `TaskList` containing tasks to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for loading and saving tasks to a file.
     * @throws JoException If an error occurs during the execution of the command, such as an invalid index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        if (this.index < 0 || this.index >= tasks.getSize()) {
            throw new JoException("This task does not exist.");
        } else {
            Task removedTask = tasks.getTask(this.index);
            tasks.deleteTask(this.index);
            storage.update(tasks);
            ui.modifyListResult(removedTask, tasks, false);
        }
    }

    /**
     * Checks whether the command results in exiting the application.
     *
     * @return `false` since deleting a task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
