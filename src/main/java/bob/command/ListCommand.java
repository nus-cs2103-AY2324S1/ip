package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextUi;

/**
 * The ListCommand encapsulates logic to be executed to print out
 * the tasks within the current task list.
 */
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
