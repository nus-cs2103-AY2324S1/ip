package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.TaskList;
import Bob.ui.TextUi;

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
