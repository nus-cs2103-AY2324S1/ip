package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;

/**
 * Represents a command issued by the user to add a task to the list.
 */
public class AddCommand extends Command {
    private Task task; // The task to be added.

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.task);
        ui.showAddTask(this.task, tasks.size());
        storage.save(tasks.toFileString());
    }
}
