package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        ui.showMessage("Goodbye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
