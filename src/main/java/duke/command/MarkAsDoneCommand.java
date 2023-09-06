package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user to mark a task as done.
 */
public class MarkAsDoneCommand extends Command {
    private final int index; // The index of the task to be marked as done.

    public MarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markAsDone(this.index);
        storage.save(tasks.toFileString());
        return ui.showMarkTaskAsDone(tasks.get(this.index));
    }
}
