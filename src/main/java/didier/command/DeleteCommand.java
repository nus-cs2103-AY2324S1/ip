package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;
import didier.task.Task;

/**
 * The DeleteCommand encapsulates the logic of what happens when the user tries to delete a task from
 * the task list.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;
    private Task task;

    /**
     * Constructor for the DeleteCommand object.
     *
     * @param taskNumber The index number of the task to be deleted (1-indexed).
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.task = null;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DidierException {
        task = taskList.removeTask(taskNumber);
        storage.saveTasks(taskList);
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) {
        String outputText = "";
        if (task != null) {
            outputText += "Okay! I've removed this task:";
            outputText += String.format("\n%s", task.toString());
            outputText += String.format("\nThere are now %d tasks in your list", taskList.getSize());
        }
        return outputText;
    }
}
