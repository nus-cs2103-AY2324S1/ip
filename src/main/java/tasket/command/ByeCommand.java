package tasket.command;

import tasket.storage.Storage;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
