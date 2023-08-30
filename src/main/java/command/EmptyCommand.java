package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents an empty command that does nothing.
 */
public class EmptyCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {

    }

    @Override
    public boolean equals(Object other) {
        return other instanceof EmptyCommand;
    }
}
