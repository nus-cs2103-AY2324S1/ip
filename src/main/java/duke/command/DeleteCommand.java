package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {

    /**
     * The index of the task to be deleted.
     */
    private final int index;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of the task at the specified index.
     * Updates the UI and saves the updated list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(index);
        storage.save(tasks);
        return ui.showDeletedTask(task) + ui.showNumberOfTasks(tasks);
    }
}
