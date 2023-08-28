package command;

import exception.BobException;
import storage.StorageFile;
import task.Task;
import task.TaskList;
import ui.TextUi;

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
