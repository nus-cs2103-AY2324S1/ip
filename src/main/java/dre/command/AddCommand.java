package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.task.Task;
import dre.task.TaskList;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an AddCommand for the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding a new task to the task list.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showAddedTask(task, tasks.size());
    }
}