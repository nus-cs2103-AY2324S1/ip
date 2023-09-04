package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextGenerator;
import bob.ui.TextUi;

/**
 * The ListCommand encapsulates logic to be executed to print out
 * the tasks within the current task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;
    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        this.taskList = taskList;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getOutputMessage() throws BobException {
        return TextGenerator.getListMessage(taskList);
    }
}
