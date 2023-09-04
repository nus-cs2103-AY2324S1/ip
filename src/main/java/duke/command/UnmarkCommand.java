package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to unmark a task at specified index.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.unmarkDone(index);
        ui.unmark(tasks, task);
    }
}
