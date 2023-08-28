package command;

import exception.BobException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        ui.printListMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
