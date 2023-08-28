package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.TaskList;
import Bob.ui.TextUi;

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
