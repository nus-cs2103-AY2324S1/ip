package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

public class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
    }

    public boolean isExit() {
        return false;
    }
}
