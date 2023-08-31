package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {

    /**
     * The regular expression pattern for matching the format of a delete command.
     */
    public static final String DELETE_PATTERN = "^(delete)\\s+\\d+$";

    private int pos;

    /**
     * Constructs a DeleteCommand with the specified position of the task to delete.
     *
     * @param pos The position of the task to be deleted.
     */
    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Executes the command to delete the task at the specified position from the task list.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param ui      The user interface to display messages to the user.
     * @param storage The storage to update as needed by the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(pos); // Delete task from the task list
    }
}
