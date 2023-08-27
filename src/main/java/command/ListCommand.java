package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.printOutput(taskManager.listTasks());
    }
}
