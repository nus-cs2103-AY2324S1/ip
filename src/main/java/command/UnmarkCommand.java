package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents an unmark command where when executed, marks the specific task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand using the 1-indexed index.
     *
     * @param index The index of the task to be marked as done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.markTask(index, false));
        diskManager.saveToDisk(taskManager);
    }
}
