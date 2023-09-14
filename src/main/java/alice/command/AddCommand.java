package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to add a task to the list.
 */
public class AddCommand extends Command {
    private final Task task; // The task to be added.

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        storage.save(tasks.toFileString());
        return ui.showAddTask(this.task, tasks.size());
    }
}
