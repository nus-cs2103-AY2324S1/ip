package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * Contains the index of the task to be deleted.
 */
public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskIndexToDelete The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    /**
     * Executes the DeleteCommand.
     * Deletes the task from the task list, displays the deleted task, and saves the updated task list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the deleted task to the user.
     * @param storage The Storage object, for saving the updated task list.
     * @throws JarvisInvalidIndexException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToDelete <= 0 || taskIndexToDelete > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToDelete);
        }

        int initialSize = tasks.size();

        Task deletedTask = tasks.get(taskIndexToDelete - 1); // Lists are 0-indexed, but users see a 1-indexed list.
        tasks.remove(taskIndexToDelete - 1);
        storage.saveTasks(tasks);

        assert tasks.size() == initialSize - 1 : "Task list size should decrease by 1 after deletion";

        return ui.displayDeletedTask(deletedTask, tasks);
    }
}
