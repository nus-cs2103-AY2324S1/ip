package duke.command;

import duke.exception.InvalidIndexException;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to mark a task as completed.
 * <p>
 * When executed, this command will mark the task at the specified index as completed.
 * </p>
 */
public class MarkCommand extends Command {

    /** The index of the task to be marked as completed. */
    private final int index;

    /**
     * Constructs a new MarkCommand with the specified index.
     *
     * @param index Index of the task to be marked as completed.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command, marking the task at the specified index as completed.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage system.
     * @throws InvalidIndexException If the given index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        tasks.markTask(index);
        storage.save(tasks.getTasks());
    }

    /**
     * Specifies that executing a MarkCommand will not cause the application to exit.
     *
     * @return {@code false} indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
