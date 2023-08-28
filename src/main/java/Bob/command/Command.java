package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.TaskList;
import Bob.ui.TextUi;

public abstract class Command {
    public abstract void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException;
    public abstract boolean isExit();
}
