package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.exceptions.JoeIndexOutOfBoundsException;
import joe.tasks.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int idx;

    /**
     * Constructs a DeleteCommand with the index of the task to be deleted.
     *
     * @param idx The index of the task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the command to delete a task from the task list.
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

        Task deletedTask = tasks.get(idx - 1);
        tasks.remove(idx - 1);

        ui.print(
                String.format(
                        "Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.",
                        deletedTask, tasks.size()));

        storage.saveToFile(tasks);
    }
}
