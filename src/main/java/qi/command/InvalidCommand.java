package qi.command;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showInvalid();
    }
}
