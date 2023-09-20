package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * The MarkCommand represents a command to set a task as reminder in the task list.
 */
public class RemindCommand extends Command {
    private int index;

    /**
     * Constructs a RemindCommand object with the specified task.
     *
     * @param index The task associated with the command.
     */
    public RemindCommand(int index) {
        super(null);
        this.index = index;
    }

    /**
     * Executes the command to set a reminder on a task and updates the storage.
     *
     * @param tasks   The task list to interact with.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for saving or loading tasks.
     * @throws IOException             If there's an error while updating storage.
     * @throws InvalidTaskNumberException If an invalid task number is encountered during command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.getSize() || index < 0) throw new InvalidTaskNumberException();

        Task task = tasks.getTaskAtIndex(index);
        task.setReminder();
        storage.update(tasks.getTasks());
    }
}
