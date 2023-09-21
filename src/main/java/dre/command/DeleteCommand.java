package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int INDEX;

    /**
     * Creates a DeleteCommand with the specified task index within the task list.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.INDEX = index;
    }

    /**
     * Executes the delete command, removing a task from the task list.
     *
     * @param tasks   The current list of tasks.
     * @param ui      The UI object to show response.
     * @param storage The storage object to update stored tasks.
     */
    @Override
    public String execute(TaskList tasks,
                          Ui ui, Storage storage) {
        try {
            tasks.deleteTask(INDEX);
            return ui.generateDeletedTaskString(tasks.getTask(INDEX));
        } catch (DreException e) {
            e.printStackTrace();
            return ui.generateErrorString(e.getMessage());
        }
    }
}