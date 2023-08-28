package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.TaskList;
import Bob.ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        storageFile.saveTasks(taskList);
        ui.printGoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
