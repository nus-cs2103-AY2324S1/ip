package carbonbot.command;

import java.io.IOException;

import carbonbot.DukeException;
import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

/**
 * The command adds a task to the task list and saves the list to disk.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand object that will add the provided task
     *
     * @param task Task to be added to the list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Add the task to the TaskList
        tasks.add(this.task);
        assert(tasks.get(tasks.size()) == this.task);

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(this.task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

        // Save the TaskList to Storage
        try {
            storage.write(tasks.serialize());
        } catch (IOException ex) {
            throw new DukeException("I/O Error: Failed to write to storage. "
                    + ex.getMessage());
        }
    }
}
