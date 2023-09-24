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
     * Constructs an AddCommand to add the provided task.
     *
     * @param task Task to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CarbonStorageException {
        // Add the task to the TaskList
        tasks.add(this.task);
        assert(tasks.get(tasks.size()) == this.task);

        ui.bufferMessage("Got it. I've added this task:");
        ui.bufferMessage(this.task.toString());
        ui.bufferMessage("Now you have " + tasks.size() + " tasks in the list.");

        storage.saveTasks(tasks);
    }
}
