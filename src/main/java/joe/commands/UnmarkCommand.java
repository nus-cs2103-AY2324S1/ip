package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.exceptions.JoeIndexOutOfBoundsException;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int idx;

    /**
     * Constructs an UnmarkCommand with the index of the task to be marked as not done.
     *
     * @param idx The index of the task to be marked as not done.
     */
    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to mark a task as not done in the task list.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     * @throws JoeIndexOutOfBoundsException If the provided index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JoeIndexOutOfBoundsException {
        if (idx < 1 || idx > tasks.size()) {
            throw new JoeIndexOutOfBoundsException(idx);
        }

        tasks.get(idx - 1).markAsNotDone();

        storage.saveToFile(tasks);

        return (String.format("OK! I've marked this task as not done:%n %s", tasks.get(idx - 1)));
    }
}
