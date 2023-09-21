package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to unmark a task in the task list as done.
 * Contains the index of the task to be unmarked.
 */
public class UnmarkCommand extends Command {
    private int taskIndexToUnmark;

    /**
     * Constructs a UnmarkCommand object.
     *
     * @param taskIndexToUnmark The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndexToUnmark) {
        this.taskIndexToUnmark = taskIndexToUnmark;
    }

    /**
     * Executes the UnmarkCommand.
     * Unmarks the task as done, displays the unmarked task, and saves the updated task list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the unmarked task to the user.
     * @param storage The Storage object, for saving the updated task list.
     * @throws JarvisInvalidIndexException If the task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToUnmark <= 0 || taskIndexToUnmark > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToUnmark);
        }

        Task taskToUnmark = tasks.get(taskIndexToUnmark - 1);
        taskToUnmark.unmark();

        ui.displayUnmarkedTask(taskToUnmark);

        storage.saveTasks(tasks);
    }
}
