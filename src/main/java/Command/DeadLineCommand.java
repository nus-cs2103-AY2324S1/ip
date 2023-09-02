package command;
import duke.DukeException;
import storage.Storage;
import task.DeadLine;
import taskList.TaskList;
import ui.Ui;

/**
 * The `DeadLineCommand` class represents a command to add a deadline task to the task list in the Duke application.
 * It extends the `Command` class and is responsible for creating a new deadline task with the specified details,
 * adding it to the task list, and saving the updated task list to storage.
 * This class encapsulates the behavior of adding a deadline task to the task list.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class DeadLineCommand extends Command {
    private String description;
    private String by;

    /**
     * Constructs a new `DeadLineCommand` instance with the specified deadline task details.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time by which the task should be completed.
     */
    public DeadLineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the "Deadline" command by creating a deadline task, adding it to the task list,
     * and saving the updated list to storage. It also displays a message indicating that the task has been added.
     *
     * @param taskList The task list to which the deadline task should be added.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving the updated task list.
     * @throws DukeException An exception may be thrown if there is an error executing the
     *      command (e.g., storage error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        DeadLine deadline = new DeadLine(description, by);
        taskList.addTask(deadline);
        ui.showTaskAdded(deadline, taskList.getTaskCount());
        storage.saveTask(taskList.getTasks());
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the "Deadline" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
