package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to edit a task's properties, either by marking it as
 * complete or incomplete.
 */
public class EditCommand extends Command {
    private final int taskIndex;
    private final String operation;

    enum EditOperation {
        MARK, UNMARK
    }

    /**
     * Initializes an EditCommand with the given operation type and task index.
     *
     * @param operation   The type of edit to be performed.
     * @param taskIndex The index of the task in the task list that is to be edited.
     */
    public EditCommand(String operation, int taskIndex) {
        super(null);
        this.taskIndex = taskIndex;
        this.operation = operation;
    }

    /**
     * Executes the command to edit a task's properties based on the given operation
     * and task index.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface interactions.
     * @param storage The system's storage utility.
     * @return A string that conveys the result of the execution.
     * @throws DukeException If there is an error during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        validateTaskIndex(tasks.size(), ui);
        Task currTask = tasks.getList().get(taskIndex - 1);

        switch (operation) {
            case "mark":
                currTask.setCompleted();
                return ui.showMarkedTask(taskIndex, currTask);
            case "unmark":
                currTask.setNotCompleted();
                return ui.showUnmarkedTask(taskIndex, currTask);
            default:
                throw new DukeException("Invalid edit operation provided.");
        }
    }

    /**
     * Validates the task index.
     *
     * @param taskSize The total number of tasks.
     * @param ui       The user interface interactions.
     * @throws DukeException If the task index is invalid.
     */
    private void validateTaskIndex(int taskSize, Ui ui) throws DukeException {
        if (taskIndex <= 0 || taskIndex > taskSize) {
            throw new DukeException(ui.invalidIndexError(taskIndex));
        }
    }
}
