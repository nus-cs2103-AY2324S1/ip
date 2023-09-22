package jarvis.command;

import jarvis.exception.JarvisException;
import jarvis.exception.JarvisInvalidIndexException;
import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to mark a task in the task list as done.
 * Contains the index of the task to be marked.
 */
public class MarkCommand extends Command {
    private int taskIndexToMark;

    /**
     * Constructs a MarkCommand object.
     *
     * @param taskIndexToMark The index of the task to be marked.
     */
    public MarkCommand(int taskIndexToMark) {
        this.taskIndexToMark = taskIndexToMark;
    }

    /**
     * Executes the MarkCommand.
     * Marks the task as done, displays the marked task, and saves the updated task list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the marked task to the user.
     * @param storage The Storage object, for saving the updated task list.
     * @throws JarvisInvalidIndexException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (taskIndexToMark <= 0 || taskIndexToMark > tasks.size()) {
            throw new JarvisInvalidIndexException(taskIndexToMark);
        }

        Task taskToMark = tasks.get(taskIndexToMark - 1);
        taskToMark.mark();
        storage.saveTasks(tasks);
        return ui.displayMarkedTask(taskToMark);

    }
}
