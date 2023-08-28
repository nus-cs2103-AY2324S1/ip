package command;

import exception.BobException;
import exception.BobTaskOutOfBoundsException;
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
        try {
            Task deletedTask = taskList.deleteTask(taskNumber);
            //TODO: Ui print delete message
        } catch (BobTaskOutOfBoundsException e) {
            //TODO: Ui print error message
        }
        storageFile.saveTasks(taskList);
    }
}
