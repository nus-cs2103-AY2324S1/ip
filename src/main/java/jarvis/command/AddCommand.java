package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.Task;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * Contains the task to be added.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        assert task != null : "Task to add should not be null";
        this.taskToAdd = task;
    }

    /**
     * Executes the AddCommand.
     * Adds the task to the task list, displays the added task, and saves the updated task list to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The Ui object, for displaying the added task to the user.
     * @param storage The Storage object, for saving the updated task list.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int initialSize = tasks.size();
        tasks.add(taskToAdd);
        assert tasks.contains(taskToAdd) : "Added task should be in the task list";
        assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a task";
        storage.saveTasks(tasks);
        return ui.displayAddedTask(taskToAdd, tasks);
    }
}
