package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private final int index; // The index of the task to be deleted.

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.index);
        tasks.delete(this.index);
        storage.save(tasks.toFileString());
        return ui.showDeleteTask(task, tasks.size());
    }
}
