package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command is responsible for adding a specified task to the task list,
 * updating the user interface, and saving the task data.
 */
public class AddCommand implements Command {
    private final Task restoreTask;
    private String indexTask;

    /**
     * Constructs an AddCommand with the specified task and an optional index.
     *
     * @param task  The task to be added.
     * @param index The optional index for task position (not used in this implementation).
     */
    public AddCommand(Task task, String index) {
        this.restoreTask = task;
        this.indexTask = index;
    }

    /**
     * Executes the add command, adding the specified task to the task list,
     * updating the user interface with a confirmation message, and saving the task data.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for saving task data.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.restoreTask);
        ui.sendMessage("Got it. I've added this task:\n" + "\t" + this.restoreTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
        storage.editData(tasks);
    }

    /**
     * Loads tasks from storage.
     *
     * @param tasks The TaskList to which tasks would be loaded.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Returns an EmptyCommand as an undo operation (not fully implemented).
     *
     * @param tasks The TaskList containing the tasks.
     * @return An EmptyCommand instance.
     * @throws DukeException If an error occurs during undo.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new EmptyCommand();
    }
}
