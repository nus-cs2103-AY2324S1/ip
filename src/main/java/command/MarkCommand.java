package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;

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
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException {
        String res = taskManager.markTask(index, true);
        diskManager.saveToDisk(taskManager);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof MarkCommand) {
            MarkCommand temp = (MarkCommand) other;
            return temp.index == this.index;
        }
        return false;
    }
}
