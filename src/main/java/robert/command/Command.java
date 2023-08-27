package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.exception.RobertException;

public class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
    }

    public boolean isExit() {
        return false;
    }
}
