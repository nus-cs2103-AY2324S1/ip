package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
/**
 * Represents a command to add a new task to the task list.
 */
public class AddCommand extends Command {

    /**
     * The task to be added.
     */
    private final Task task;

    /**
     * Constructs a new AddCommand object with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the addition of the task to the list.
     * Updates the UI and saves the updated list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showAddedTask(task);
        storage.save(tasks);
    }
}
