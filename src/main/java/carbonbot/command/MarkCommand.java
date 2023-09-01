package carbonbot.command;

import java.io.IOException;

import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

/**
 * The command sets the marked status of a task in the task list.
 */
public class MarkCommand extends Command {
    private final int taskIdx;
    private final boolean isMark;

    /**
     * Constructs a MarkCommand object.
     *
     * @param isMark  The mark status of the task
     * @param taskIdx The task index to modify the mark status
     */
    public MarkCommand(int taskIdx, boolean isMark) {
        this.taskIdx = taskIdx;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        try {
            task = tasks.get(taskIdx);
        } catch (IndexOutOfBoundsException ioe) {
            throw new DukeException("Index provided was out-of-bounds. Use the index"
                    + " number labelled for the task in the command 'list'!");
        }

        if (isMark) {
            ui.showMessage("Nice! I've marked this task as done:");
            task.markAsDone();
        } else {
            ui.showMessage("OK, I've marked this task as not done yet:");
            task.markAsUndone();
        }
        ui.showMessage(task.toString());

        // Save the TaskList to Storage
        try {
            storage.write(tasks.serialize());
        } catch (IOException ex) {
            throw new DukeException("I/O Error: Failed to write to storage. "
                    + ex.getMessage());
        }
    }
}
