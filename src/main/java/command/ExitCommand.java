package command;

import exception.BobException;
import storage.StorageFile;
import task.TaskList;
import ui.TextUi;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        storageFile.saveTasks(taskList);
        ui.printGoodbyeMessage();
    }
}
