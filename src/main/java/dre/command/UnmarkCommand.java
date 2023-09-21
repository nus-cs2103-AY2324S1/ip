package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

/**
 * Represents a command to unmark a task.
 */
public class UnmarkCommand extends Command {
    private final int TASK_INDEX;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.TASK_INDEX = index;
    }

    /**
     * Executes the unmark command, marking a task as undone.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(TASK_INDEX);
            return ui.generateUnmarkedTaskString(tasks.getTask(TASK_INDEX));
        } catch (DreException e) {
            return ui.generateErrorString(e.getMessage());
        }
    }
}