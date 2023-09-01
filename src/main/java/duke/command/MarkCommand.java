package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    /**
     * The index of the task to be marked.
     */
    private final int index;

    /**
     * Constructs a new MarkCommand object.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the task as done.
     * Updates the UI and saves the updated list to storage.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.mark(index);
        ui.showMarkedTask(task);
        storage.save(tasks);
    }
}
