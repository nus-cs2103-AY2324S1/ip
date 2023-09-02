package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task to be added.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command, adding the task to the task list, displaying a message, and saving to storage.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage handler.
     * @throws DukeException If there's an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        // Add the task to the task list and display a message
        ui.showMessage(String.format("Got it. I've added this task:\n\t%s\n", tasks.addTask(this.task)));

        // Display the updated task list status
        ui.showMessage(tasks.status());

        // Save the updated task list to storage
        storage.save(tasks);
    }
}
