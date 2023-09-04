package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextGenerator;
import bob.ui.TextUi;

/**
 * The ExitCommand encapsulates logic to be executed when
 * the application should be terminated.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getOutputMessage() {
        return TextGenerator.getFarewellMessage();
    }
}
