package Bob.command;

import Bob.exception.BobException;
import Bob.storage.StorageFile;
import Bob.task.Task;
import Bob.task.TaskList;
import Bob.ui.TextUi;

/**
 * The DeleteCommand class encapsulates logic that can be executed
 * to delete tasks from the current task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructor of the DeleteCommand Class.
     *
     * @param taskNumber
     */
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
