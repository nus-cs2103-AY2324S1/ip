package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;

/**
 * Represents a delete command where when executed, deletes the specific task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs the DeleteCommand using the 1-indexed index.
     *
     * @param index The index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException {
        String res = taskManager.deleteTask(index);
        diskManager.saveToDisk(taskManager);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeleteCommand) {
            DeleteCommand temp = (DeleteCommand) other;
            return temp.index == this.index;
        }

        return false;
    }
}
