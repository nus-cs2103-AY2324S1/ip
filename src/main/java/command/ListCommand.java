package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a list command where when executed, prints the list of current tasks to the ui.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.printOutput(taskManager.listTasks());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
