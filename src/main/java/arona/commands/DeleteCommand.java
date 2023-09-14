package arona.commands;

import arona.storage.Storage;
import arona.task.Task;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to delete a task. When executed, this command removes the
 * specified task from the task list, deletes it from storage, and displays a
 * confirmation message to the user interface.
 */
public class DeleteCommand extends Command implements UndoableCommand {
    private Storage storage;
    private int taskIndex;
    private Task deletedTask;

    /**
     * Initializes a new instance of the DeleteCommand class with the specified
     * task list, user interface, storage, and task index to delete.
     *
     * @param taskList   The task list from which the task will be deleted.
     * @param ui         The user interface for displaying messages.
     * @param storage    The storage for deleting the task.
     * @param taskIndex  The index of the task to be deleted.
     */
    public DeleteCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "Delete" command by removing the specified task from the task list,
     * deleting it from storage, and displaying a confirmation message to the user interface.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            return ui.showTaskDoesNotExist();
        }

        Task task = taskList.getTasks().get(taskIndex);
        deletedTask = task; // Store the deleted task for potential undo
        taskList.getTasks().remove(taskIndex);
        storage.deleteTask(taskIndex);
        return ui.showTaskRemoved(task, taskList.getTasks().size());
    }


    /**
     * Reverses the "Delete" action by restoring the previously deleted task to the task list,
     * updating the task list and storage, and displaying a confirmation message.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String undo() {
        taskList.getTasks().add(taskIndex, deletedTask);
        return ui.showUndoDeleteCommand(deletedTask.toString());
    }

    /**
     * Retrieves the task index associated with this DeleteCommand.
     *
     * @return The task index.
     */
    public int getTaskIndex() {
        return taskIndex;
    }
}
