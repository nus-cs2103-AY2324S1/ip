package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.TaskList;
import Bob.ui.TextUi;

/**
 * The ExitCommand encapsulates logic to be executed when
 * the application should be terminated.
 */
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
