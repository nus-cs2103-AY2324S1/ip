package command;

import duke.DiskManager;
import duke.TaskManager;

/**
 * Represents a list command where when executed, prints the list of current tasks to the ui.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskManager taskManager, DiskManager diskManager) {
        return taskManager.listTasks();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
