package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextUi;

/**
 * The FindCommand Class encapsulates logic that can be executed
 * to find tasks with descriptions corresponding to a given keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        TaskList filteredTaskList = taskList.keywordFilter(keyword);
        ui.printFindMessage(filteredTaskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
