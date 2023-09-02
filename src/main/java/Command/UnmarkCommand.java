package command;
import duke.DukeException;
import storage.Storage;
import task.Task;
import taskList.TaskList;
import ui.Ui;

/**
 * The `UnmarkCommand` class represents a command to unmark a task as completed in the BloopBot application.
 * It extends the `Command` class and is responsible for executing the unmark operation.
 * This class takes a task index as input and marks the corresponding task as not completed.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new `UnmarkCommand` instance with the specified task index.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by setting the selected task as not completed.
     *
     * @param taskList The task list where the task is stored.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for saving task data.
     * @throws DukeException If there is an issue executing the unmark command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new DukeException("Invalid task index.");
        }

        try {
            Task taskToUnmark = taskList.getTasks().get(taskIndex);

            if (taskToUnmark.checkIsDone()) {
                taskToUnmark.isNotCompleted();
                ui.showTaskUnmarked(taskToUnmark);
                storage.saveTask(taskList.getTasks());
            } else {
                throw new DukeException("This task is already marked as done.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index is out of range!");
        }
    }

    /**
     * Indicates whether this command should exit the application.
     *
     * @return `false` because the unmark command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
