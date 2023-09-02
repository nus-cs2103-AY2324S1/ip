package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;
import didier.exception.FileCorruptedException;
import didier.exception.TaskNumberException;
import didier.task.Task;

/**
 * The MarkCommand encapsulates the logic of what occurs when the user tries to mark
 * a task as done or undone.
 */
public class MarkCommand extends Command {

    private final boolean isMark;
    private final int taskNumber;
    private Task task;

    /**
     * The constructor for the MarkCommand object.
     *
     * @param isMark Whether the task should be marked or unmarked.
     * @param taskNumber The index number of the task to be marked or unmarked (1-indexed).
     */
    public MarkCommand(boolean isMark, int taskNumber) {
        this.isMark = isMark;
        this.taskNumber = taskNumber;
        this.task = null;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws TaskNumberException, FileCorruptedException {
        task = taskList.getTask(taskNumber);
        if (isMark) {
            task.markAsDone();
            storage.saveTasks(taskList);
        } else {
            task.markAsNotDone();
            storage.saveTasks(taskList);
        }
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) throws DidierException {
        String outputText = "";
        if (task != null) {
            if (isMark) {
                outputText += "Okay! I've marked the following task as done:";
            } else {
                outputText += "Okay! I've marked the following task as undone:";
            }
            outputText += String.format("\n%s", task.toString());
        }
        return outputText;
    }
}
