package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to unmark a task as done. When executed, this command
 * unmarks a specified task in the task list and updates its status in storage.
 */
public class UnmarkCommand extends Command implements UndoableCommand {
    private Storage storage;
    private int taskIndex;

    /**
     * Initializes a new instance of the UnMarkCommand class with the specified
     * task list, user interface, storage, and task index.
     *
     * @param taskList   The task list containing the tasks.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage responsible for loading and saving tasks.
     * @param taskIndex  The index of the task to be unmarked in the task list.
     */
    public UnmarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by unmarking the specified task in the task list,
     * updating its status in storage, and displaying the result to the user.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            return ui.showTaskDoesNotExist();
        }
        taskList.getTasks().get(taskIndex).unMark();
        storage.updateTaskStatusAsUnmarked(taskIndex);
        return ui.showTaskUnmarked(taskList.getTasks().get(taskIndex));
    }

    /**
     * Reverses the unmark action by marking the specified task as done in the task
     * list, updating its status in storage, and displaying the result to the user.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String undo() {
        taskList.getTasks().get(taskIndex).mark();
        storage.updateTaskStatusAsMarked(taskIndex);
        return ui.showUndoUnmarkCommand(taskList.getTasks().get(taskIndex));
    }

    /**
     * Retrieves the task index associated with this UnmarkCommand.
     *
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }
}
