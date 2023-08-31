package duke.command;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to delete a specific task from the task list.
 * <p>
 * This command deletes a task identified by its index from the provided task list.
 * Once deleted, it also saves the updated list to storage.
 * </p>
 */
public class DeleteCommand extends Command {

    /** The index of the task to be deleted. */
    private final int index;

    /**
     * Constructs a new DeleteCommand with the specified index.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list based on its index and saves the updated list to storage.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage system.
     * @throws InvalidIndexException If the provided index is not valid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
    }

    /**
     * Specifies that executing a DeleteCommand will not cause the application to exit.
     *
     * @return {@code false} as deleting a task doesn't terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
