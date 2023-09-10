package miles.command;

import miles.TaskList;
import miles.Ui;
import miles.Storage;

/**
 * Represents exiting the program command.
 */
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
