package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand implements Command {

    private Task task;

    /**
     * Constructs an AddEventCommand with the specified event task.
     *
     * @param task The event task to be added.
     */
    public AddEventCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the given event task to the task list and update storage.
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
