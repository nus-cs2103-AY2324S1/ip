package command;

import duke.DukeException;
import storage.Storage;
import task.Task;
import taskList.TaskList;
import ui.Ui;

/**
 * The `MarkCommand` class represents a command to mark a task as completed in the Duke application.
 * It extends the `Command` class and is responsible for updating the status of a specified task to "done."
 * This class encapsulates the behavior of marking a task as completed and saving the updated task list to storage.
 * It also performs validation to ensure that the specified task index is valid and that the task is
 * not already marked as done.
 * If the specified task index is out of range or the task is already marked as done, an exception is thrown.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a new `MarkCommand` instance with the specified task index.
     *
     * @param taskIndex The index of the task to be marked as completed.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "Mark" command by marking the specified task as completed and saving the updated list to storage.
     *
     * @param taskList The task list containing the task to be marked.
     * @param ui       The user interface for displaying feedback to the user.
     * @param storage  The storage component for saving the updated task list.
     * @throws DukeException An exception may be thrown if:
     *                       - The specified task index is out of range.
     *                       - The task is already marked as done.
     *                       - There is an error executing the command (e.g., storage error).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= taskList.getTaskCount()) {
            throw new DukeException("Invalid task index.");
        }

        try {
            Task taskToMark = taskList.getTasks().get(taskIndex);

            if (!taskToMark.checkIsDone()) {
                taskToMark.isCompleted();
                ui.showTaskMarked(taskToMark);
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
     * @return `false` because the "Mark" command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
