package command;

import exception.BobException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public abstract class Command {
    public abstract void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException;
}
