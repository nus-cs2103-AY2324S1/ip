package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a mark command where when executed, marks the specified task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand using the 1-indexed index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.markTask(index, true));
        diskManager.saveToDisk(taskManager);
    }
}
