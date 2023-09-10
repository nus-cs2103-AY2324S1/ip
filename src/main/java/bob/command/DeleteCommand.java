package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.TextGenerator;

/**
 * The DeleteCommand class encapsulates logic that can be executed
 * to delete tasks from the current task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private Task task;

    /**
     * Constructor of the DeleteCommand Class.
     *
     * @param taskNumber Index of task to be deleted from current list of tasks
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        this.task = taskList.getTask(taskNumber - 1);
        taskList.deleteTask(taskNumber);
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getOutputMessage() {
        return TextGenerator.getDeleteMessage(task);
    }
}
