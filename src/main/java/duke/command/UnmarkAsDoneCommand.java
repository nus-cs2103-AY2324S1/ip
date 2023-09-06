package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user to mark a task as not done.
 */
public class UnmarkAsDoneCommand extends Command {
    private final int index; // The index of the task to be marked as not done.

    public UnmarkAsDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmarkAsDone(this.index);
        storage.save(tasks.toFileString());
        return ui.showUnmarkTaskAsDone(tasks.get(this.index));
    }
}
