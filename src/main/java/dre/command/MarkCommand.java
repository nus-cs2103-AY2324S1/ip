package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.task.TaskList;
import dre.exception.DreException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking a task as done.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     * @throws DreException If there's an error marking the task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DreException {
        try {
            tasks.mark(index);
            ui.showMarkedTask(tasks.getTask(index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Invalid dre.task index.");
        }
    }
}