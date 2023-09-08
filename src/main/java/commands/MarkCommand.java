package commands;

import functions.*;
import java.io.IOException;
import tasks.*;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {

    protected int num;

    /**
     * Constructs a MarkCommand with the task number to be marked as completed.
     *
     * @param num The number of the task to be marked as completed.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the MarkCommand to mark a task as completed, display a message, and save changes.
     *
     * @param tasks   The TaskList to mark the task in.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface for saving data.
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.markTask(this.num);
        storage.saveFiles(tasks.showList());
        return ui.showMarkMsg(t);
    }
}

