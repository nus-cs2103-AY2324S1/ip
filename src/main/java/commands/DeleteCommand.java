package commands;

import functions.*;
import tasks.*;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    protected int num;

    /**
     * Constructs a DeleteCommand with the task number to be deleted.
     *
     * @param num The number of the task to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Executes the DeleteCommand to delete a task, display a message, and save changes.
     *
     * @param tasks   The TaskList to delete the task from.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface for saving data.
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.deleteTask(num);
        ui.showDeleteMsg(t, tasks.numOfTasks());
        storage.saveFiles(tasks.showList());
    }
}

