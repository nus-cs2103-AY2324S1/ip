package commands;

import java.io.IOException;

import functions.Storage;
import functions.TaskList;
import functions.Ui;
import tasks.Task;
import utilities.Messages;

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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.deleteTask(num);
            storage.saveFiles(tasks.showList());
            return ui.showDeleteMsg(t, tasks.numOfTasks());
        } catch (IndexOutOfBoundsException e) {
            return ui.showErrorMsg(Messages.INVALID_NUMBER);
        }
    }
}

