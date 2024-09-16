package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;

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
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException {
        String res = taskManager.markTask(index, false);
        diskManager.saveToDisk(taskManager);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof UnmarkCommand) {
            UnmarkCommand temp = (UnmarkCommand) other;
            return temp.index == this.index;
        }
        return false;
    }
}
