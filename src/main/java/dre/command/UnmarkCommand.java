package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, unmarking a task.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(index);
            ui.showUnmarkedTask(tasks.getTask(index));
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}