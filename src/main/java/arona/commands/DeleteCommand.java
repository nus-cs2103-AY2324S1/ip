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
public class DeleteCommand extends Command {
    private Storage storage;
    private int taskIndex;

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
     */
    @Override
    public void execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            ui.showTaskDoesNotExist(taskIndex);
            return;
        }
        Task task = taskList.getTasks().get(taskIndex);
        taskList.getTasks().remove(taskIndex);
        storage.deleteTask(taskIndex);
        ui.showTaskRemoved(task, taskList.getTasks().size());
    }
}
