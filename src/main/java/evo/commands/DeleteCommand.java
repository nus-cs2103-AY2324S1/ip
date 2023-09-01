package evo.commands;

import evo.storage.Storage;
import evo.tasks.Task;
import evo.tasks.TaskList;
import evo.ui.Ui;

/**
 * The DeleteCommand class represents a command to delete a task from a TaskList.
 * When executed, it removes the specified task from the TaskList and displays a confirmation message to the user.
 */
public class DeleteCommand extends Command {
    /**
     * The task number (in string format) to be deleted.
     */
    String taskNumberToDelete;

    /**
     * Constructs a DeleteCommand with the specified task number to delete.
     *
     * @param taskNumberToDelete The task number (in string format) to be deleted.
     */
    public DeleteCommand(String taskNumberToDelete) {
        this.taskNumberToDelete = taskNumberToDelete;
    }

    /**
     * Executes the DeleteCommand to delete a task from the TaskList, updates the UI, and optionally the storage.
     *
     * @param tasksList The TaskList containing the tasks to be managed.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        // Deletes a task from taskList
        int taskNumberToDelete = Integer.parseInt(this.taskNumberToDelete) - 1;
        Task deletedTask = tasksList.get(taskNumberToDelete);
        tasksList.deleteTask(taskNumberToDelete);
        ui.showText("Noted. I've removed this task:");
        ui.showText("  " + deletedTask.toString());
        if (tasksList.tasksListLength() <= 1) {
            ui.showText("Now you have " + tasksList.tasksListLength() + " task in the list.\n");
        } else {
            ui.showText("Now you have " + tasksList.tasksListLength() + " tasks in the list.\n");
        }
    }
}