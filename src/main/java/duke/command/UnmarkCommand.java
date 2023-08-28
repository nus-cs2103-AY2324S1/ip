package duke.command;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * Represents a duke.command to unmark a task as completed.
 * <p>
 * When executed, this duke.command will unmark the task at the specified index as completed.
 * </p>
 */
public class UnmarkCommand extends Command {

    /** The index of the task to be unmarked as completed. */
    private final int index;

    /**
     * Constructs a new UnmarkCommand with the specified index.
     *
     * @param index Index of the task to be unmarked as completed.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark duke.command, unmarking the task at the specified index as completed.
     *
     * @param tasks List of duke.tasks.
     * @param ui User interface.
     * @param storage Storage system.
     * @throws InvalidIndexException If the given index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
    }

    /**
     * Specifies that executing an UnmarkCommand will not cause the application to exit.
     *
     * @return {@code false} indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
