package command;
import duke.DukeException;
import storage.Storage;
import task.Task;
import taskList.TaskList;
import ui.Ui;

/**
 * The `ToDoCommand` class represents a command to add a "ToDo" task to the task list in the Duke application.
 * It extends the `Command` class and is responsible for adding a specified task to the task list.
 * This class encapsulates the behavior of creating a "ToDo" task and saving it to storage.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class ToDoCommand extends Command {
    private Task taskToDo;

    /**
     * Constructs a new `ToDoCommand` instance with the specified `Task` to be added as a "ToDo" task.
     *
     * @param taskToDo The `Task` to be added as a "ToDo" task.
     */
    public ToDoCommand(Task taskToDo) {
        this.taskToDo = taskToDo;
    }

    /**
     * Executes the "ToDo" command by adding the specified `Task` to the task
     * list and saving the updated list to storage.
     *
     * @param taskList The task list to which the task should be added.
     * @param ui       The user interface for interacting with the user (not used in this command).
     * @param storage  The storage component for saving the updated task list.
     * @throws DukeException An exception may be thrown if there is an error
     *       executing the command (e.g., storage error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(taskToDo);
        storage.saveTask(taskList.getTasks());
        ui.showTaskAdded(taskToDo, taskList.getTaskCount());
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the "ToDo" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
