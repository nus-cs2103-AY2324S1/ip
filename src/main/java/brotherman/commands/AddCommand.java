package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.Task;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to add a task to the task list
 */

public class AddCommand extends Command {

    /**
     * Task to be added
     */
    private Task task;
    /**
     * Constructor for AddCommand
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the command to add a task to the task list
     * @param task Task list to be added to
     * @param ui Ui to show the user the task has been added
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.add(this.task);
        ui.showTaskAdded(task);
    }
}
