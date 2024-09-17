package duke.command;

import duke.DukeException;
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
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        boolean success = tasks.unmarkDone(index);
        return ui.unmark(tasks.get(index), success);
    }
}
