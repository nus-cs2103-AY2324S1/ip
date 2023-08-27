package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

public class ClearCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.clearTasks();
        ui.showMessage("Understood. I've removed every task in your list.\n"
                + "Now your list of tasks is empty!");
    }
}
