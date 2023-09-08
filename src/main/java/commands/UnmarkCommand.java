package commands;

import functions.*;
import java.io.IOException;
import tasks.*;

/**
 * Represents a command to unmark a completed task.
 */
public class UnmarkCommand extends Command {

    protected int num;

    /**
     * Constructs an UnmarkCommand with the task number to be unmarked.
     *
     * @param num The number of the task to be unmarked.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the UnmarkCommand to unmark a completed task, display a message, and save changes.
     *
     * @param tasks   The TaskList to unmark the task in.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface for saving data.
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.unmarkTask(this.num);
        storage.saveFiles(tasks.showList());
        return ui.showUnmarkMsg(t);
    }
}

