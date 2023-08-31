package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand implements Command {

    /**
     * The regular expression pattern for matching the format of a todo command.
     */
    public static final String TODO_PATTERN = "^(todo)\\s+.+";

    private Task task;

    /**
     * Constructs an AddTodoCommand with the specified todo task.
     *
     * @param task The todo task to be added.
     */
    public AddTodoCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the given todo task to the task list and update storage.
     *
     * @param tasks   The task list that the task will be added to.
     * @param ui      The user interface to display messages to the user.
     * @param storage The storage to update with the new task information.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);             // Add task to the task list
        storage.addLine(task.toString()); // Update storage with the new task
    }
}
