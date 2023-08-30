package Bob.command;

import Bob.exception.BobException;

import Bob.storage.StorageFile;

import Bob.task.Task;
import Bob.task.TaskList;

import Bob.ui.TextUi;

/**
 * The MarkCommand encapsulates logic to be executed to modify the
 * completion status of a specific task.
 */
public class MarkCommand extends Command{
    private int taskNumber;
    private boolean isDone;

    /**
     * Constructor of the MarkCommand Class.
     *
     * @param taskNumber Index of task to mark/unmark
     * @param isDone Completion status of task
     */
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
