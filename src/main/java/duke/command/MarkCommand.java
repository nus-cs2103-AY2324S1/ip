package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to mark a task at specified index.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        Task task = tasks.markDone(index);
        ui.mark(tasks, task);
    }
}
