package didier.command;

import didier.Storage;
import didier.UI;
import didier.exception.FileCorruptedException;
import didier.exception.TaskNumberException;
import didier.task.Task;
import didier.TaskList;

/**
 * The MarkCommand encapsulates the logic of what occurs when the user tries to mark
 * a task as done or undone.
 */
public class MarkCommand extends Command {

    private boolean isMark;
    private int taskNumber;

    /**
     * The constructor for the MarkCommand object.
     *
     * @param isMark Whether the task should be marked or unmarked.
     * @param taskNumber The index number of the task to be marked or unmarked (1-indexed).
     */
    public MarkCommand(boolean isMark, int taskNumber) {
        this.isMark = isMark;
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskNumberException, FileCorruptedException {
        Task task = taskList.getTask(taskNumber);
        if (isMark){
            task.markAsDone();
            ui.botPrintTaskMarkedDone(task, true);
        } else {
            task.markAsNotDone();
            ui.botPrintTaskMarkedDone(task, false);
        }
        storage.saveTasks(taskList);
    }
}
