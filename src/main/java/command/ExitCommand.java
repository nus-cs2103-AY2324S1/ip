package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents an exit command where when executed, exits the application.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.closeUi();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ExitCommand;
    }
}
