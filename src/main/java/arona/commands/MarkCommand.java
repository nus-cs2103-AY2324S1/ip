package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to mark a task as done. When executed, this command marks
 * the specified task as done, updates its status in storage, and displays a
 * confirmation message.
 */
public class MarkCommand extends Command implements UndoableCommand {
    private Storage storage;
    private int taskIndex;

    /**
     * Initializes a new instance of the MarkCommand class with the specified
     * task list, user interface, storage, and task index.
     *
     * @param taskList   The task list containing the tasks.
     * @param ui         The user interface to interact with the user.
     * @param storage    The storage responsible for loading and saving tasks.
     * @param taskIndex  The index of the task to be marked as done.
     */
    public MarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "Mark" command by marking the specified task as done, updating
     * its status in storage, and displaying a confirmation message to the user.
     * If the task index is out of bounds, an error message is shown.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            return ui.showTaskDoesNotExist();
        }
        taskList.getTasks().get(taskIndex).mark();
        storage.updateTaskStatusAsMarked(taskIndex);
        return ui.showTaskMarkedAsDone(taskList.getTasks().get(taskIndex));
    }

    /**
     * Reverses the mark action by unmarking the specified task in the task list,
     * updating its status in storage, and displaying the result to the user.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String undo() {
        taskList.getTasks().get(taskIndex).unMark();
        storage.updateTaskStatusAsUnmarked(taskIndex);
        return ui.showUndoMarkCommand(taskList.getTasks().get(taskIndex));
    }

    /**
     * Retrieves the task index associated with this MarkCommand.
     *
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }
}
