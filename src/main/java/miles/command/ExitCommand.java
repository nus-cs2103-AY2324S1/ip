package miles.command;

import miles.TaskList;
import miles.Ui;
import miles.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
