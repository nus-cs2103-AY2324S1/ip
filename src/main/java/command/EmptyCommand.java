package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;

public class EmptyCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {

    }
}
