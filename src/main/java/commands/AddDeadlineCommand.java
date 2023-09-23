package commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class AddDeadlineCommand implements Command {

    private Task task;

    /**
     * Constructs an AddDeadlineCommand with the specified deadline task.
     *
     * @param task The deadline task to be added.
     */
    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the given deadline task to the task list and update storage.
     *
     * @param tasks   The task list that the task will be added to.
     * @param ui      The user interface to display messages to the user.
     * @param storage The storage to update with the new task information.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (task == null || task.isNotValid()) {
            return "Invalid Input. Please Try Again.";
        }
        storage.addLine(task.toString());
        return tasks.add(task);
    }
}
