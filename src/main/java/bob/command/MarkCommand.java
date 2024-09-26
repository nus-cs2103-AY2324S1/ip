package bob.command;

import bob.exception.BobException;
import bob.exception.BobInvalidTaskNumberException;
import bob.storage.StorageFile;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.TextGenerator;

import java.util.ArrayList;

/**
 * The MarkCommand encapsulates logic to be executed to modify the
 * completion status of one or multiple tasks.
 */
public class MarkCommand extends Command {
    private ArrayList<Integer> taskNumbers;
    private boolean isDone;
    private TaskList changedTasks;

    /**
     * Constructor for the MarkMultipleCommand class.
     *
     * @param taskNumbers List of task numbers representing task to be mark/unmark
     * @param isDone Completion status of the tasks
     */
    public MarkCommand(ArrayList<Integer> taskNumbers, boolean isDone) {
        this.taskNumbers = taskNumbers;
        this.isDone = isDone;
        this.changedTasks = new TaskList();
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile) throws BobException {
        try {
            for (Integer i: taskNumbers) {
                Task currTask = taskList.getTask(i - 1);
            }
        } catch (BobInvalidTaskNumberException e) {
            String message = "One of the tasks you are trying to mark/unmark is non-existent!\n";
            String recommendation = "Use the command: \"list\" to find out what tasks you have.";
            throw new BobInvalidTaskNumberException(message + recommendation);
        }
        for (Integer i: taskNumbers) {
            Task currTask = taskList.getTask(i - 1);
            if (isDone) {
                currTask.markAsDone();
            } else {
                currTask.unmarkTask();
            }
            changedTasks.addTask(currTask);
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
            return TextGenerator.getMarkMultipleMessage(changedTasks);
        } else {
            return TextGenerator.getUnmarkMultipleMessage(changedTasks);
        }
    }
}
