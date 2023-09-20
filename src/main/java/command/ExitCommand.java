package command;

import duke.DiskManager;
import duke.TaskManager;

/**
 * Represents an exit command where when executed, exits the application.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskManager taskManager, DiskManager diskManager) {
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }
}
