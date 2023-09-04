package bob.command;

import bob.exception.BobException;
import bob.exception.BobInvalidTaskNumberException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextGenerator;
import bob.ui.TextUi;

/**
 * The FindCommand Class encapsulates logic that can be executed
 * to find tasks with descriptions corresponding to a given keyword.
 */
public class FindCommand extends Command {

    private String keyword;
    private TaskList filteredTaskList;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        filteredTaskList = taskList.keywordFilter(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getOutputMessage() throws BobInvalidTaskNumberException {
        return TextGenerator.getFindMessage(filteredTaskList);
    }
}
