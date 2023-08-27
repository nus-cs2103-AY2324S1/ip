package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.closeUi();
    }
}
