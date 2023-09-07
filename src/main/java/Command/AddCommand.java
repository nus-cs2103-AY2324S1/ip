package command;
import duke.DukeException;
import storage.Storage;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

/**
 * The `AddCommand` class represents a command to add a task to the task list in the Duke application.
 * It extends the `Command` class and is responsible for adding a specified task to the task list,
 * displaying a message indicating the task has been added, and saving the updated task list to storage.
 * This class encapsulates the behavior of adding a task to the task list.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs a new `AddCommand` instance with the specified task to be added.
     *
     * @param taskToAdd The task to be added to the task list.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the "Add" command by adding the specified task to the task list,
     * displaying a message indicating the task has been added, and saving the updated task list to storage.
     *
     * @param taskList The task list to which the task should be added.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving the updated task list.
     * @return A message indicating that the task has been added.
     * @throws DukeException An exception may be thrown if there is an error executing
     *      the command (e.g., storage error).
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(taskToAdd);
        storage.saveTask(taskList.getTasks());
        return ui.showTaskAdded(taskToAdd, taskList.getTaskCount());

    }
}
