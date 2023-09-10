package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.exception.CarbonStorageException;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CarbonStorageException {
        // Add the task to the TaskList
        tasks.add(this.task);

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(this.task.toString());
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);
    }
}
