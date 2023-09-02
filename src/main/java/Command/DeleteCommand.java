package command;
import duke.DukeException;
import storage.Storage;
import task.Task;
import taskList.TaskList;
import ui.Ui;

/**
 * The `DeleteCommand` class represents a command to delete a task from the task list in the Duke application.
 * It extends the `Command` class and is responsible for deleting a specified task from the task list,
 * displaying a message about the deleted task, and saving the updated task list to storage.
 * This class encapsulates the behavior of deleting a task from the task list.
 * It performs validation to ensure that the specified task index is valid.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new `DeleteCommand` instance with the specified task index to be deleted.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "Delete" command by deleting the specified task from the task list,
     * displaying a message about the deleted task, and saving the updated task list to storage.
     *
     * @param taskList The task list from which the task should be deleted.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving the updated task list.
     * @throws DukeException An exception may be thrown if:
     *                       - The specified task index is out of range.
     *                       - There is an error executing the command (e.g., storage error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task removedTask = taskList.deleteTask(taskIndex);
            ui.showDeletedTask(removedTask, taskList.getTaskCount());
            storage.saveTask(taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index is out of range!");
        }
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the "Delete" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

