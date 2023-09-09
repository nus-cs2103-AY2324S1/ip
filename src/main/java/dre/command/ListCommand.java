package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, displaying all tasks.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show the list of tasks.
     * @param storage The storage object (unused for this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showTasks(tasks);
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}