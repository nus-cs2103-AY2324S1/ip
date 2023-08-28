package command;

import exception.BobException;
import storage.StorageFile;
import task.Task;
import task.TaskList;
import ui.TextUi;

public class MarkCommand extends Command{
    private int taskNumber;
    private boolean isDone;

    public MarkCommand(int taskNumber, boolean isDone) {
        this.taskNumber = taskNumber;
        this.isDone = isDone;
    }
    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        Task task = taskList.getTask(taskNumber - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmarkTask();
        }
        ui.printMarkMessage(task, isDone);
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
