package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.exceptions.JoeIndexOutOfBoundsException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int idx;

    /**
     * Constructs a MarkCommand with the index of the task to be marked as done.
     *
     * @param idx The index of the task to be marked as done.
     */
    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to mark a task as done in the task list.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage for saving and loading tasks.
     * @throws JoeIndexOutOfBoundsException If the provided index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoeIndexOutOfBoundsException {
        if (idx < 1 || idx > tasks.size()) {
            throw new JoeIndexOutOfBoundsException(idx);
        }

        tasks.get(idx - 1).markAsDone();
        ui.print(String.format("Nice! I've marked this task as done:%n %s", tasks.get(idx - 1)));

        storage.saveToFile(tasks);
    }
}
