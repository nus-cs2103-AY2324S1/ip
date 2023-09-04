package bob.command;

import bob.exception.BobException;
import bob.storage.StorageFile;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.TextGenerator;
import bob.ui.TextUi;
import org.w3c.dom.Text;

/**
 * The MarkCommand encapsulates logic to be executed to modify the
 * completion status of a specific task.
 */
public class MarkCommand extends Command {
    private int taskNumber;
    private boolean isDone;
    private Task task;

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
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        this.task = taskList.getTask(taskNumber - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmarkTask();
        }
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getOutputMessage() throws BobException {
        if (isDone) {
            return TextGenerator.getMarkMessage(this.task);
        } else {
            return TextGenerator.getUnmarkMessage(this.task);
        }
    }
}
