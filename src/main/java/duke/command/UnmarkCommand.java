package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand extends Command {

    /**
     * The index of the task to be unmarked.
     */
    private final int index;

    /**
     * Constructs a new UnmarkCommand object.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, marking the task as not done.
     * Updates the UI and saves the updated list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmark(index);
        ui.showUnmarkedTask(task);
        storage.save(tasks);
    }
}
