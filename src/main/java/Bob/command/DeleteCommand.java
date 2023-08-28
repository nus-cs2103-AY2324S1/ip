package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.Task;
import Bob.task.TaskList;
import Bob.ui.TextUi;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        ui.printDeleteMessage(deletedTask);
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
