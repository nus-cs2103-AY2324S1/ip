package command;

import exception.BobException;
import exception.BobInvalidTaskNumberException;
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
        //TODO: Ui print delete message
        storageFile.saveTasks(taskList);
    }
}
