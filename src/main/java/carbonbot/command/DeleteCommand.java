package carbonbot.command;

import java.io.IOException;

import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

/**
 * The command deletes a task from the task list and saves the list to disk.
 */
public class DeleteCommand extends Command {
    private final int taskIdx;

    /**
     * Constructs a DeleteCommand object that will delete the task at the provided index
     *
     * @param taskIdx Task index to be deleted from the list
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.get(taskIdx);
            tasks.delete(taskIdx);

            assert tasks.get(taskIdx) != task;

            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException ie) {
            throw new DukeException("Index provided was out-of-bounds. Use the index"
                    + " number labelled for the task in the command 'list'!");
        }

        try {
            storage.write(tasks.serialize());
        } catch (IOException ex) {
            throw new DukeException("I/O Error: Failed to write to storage. "
                    + ex.getMessage());
        }
    }
}
