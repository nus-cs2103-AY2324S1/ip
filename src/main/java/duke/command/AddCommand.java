package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user to add a task to the list.
 */
public class AddCommand extends Command {
    private final Task task; // The task to be added.

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        storage.save(tasks.toFileString());
        return ui.showAddTask(this.task, tasks.size());
    }
}
