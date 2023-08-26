package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.UI;
import didier.exception.DidierException;
import didier.task.Task;

/**
 * The DeleteCommand encapsulates the logic of what happens when the user tries to delete a task from
 * the task list.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor for the DeleteCommand object.
     *
     * @param taskNumber The index number of the task to be deleted (1-indexed).
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DidierException {
        Task task = taskList.removeTask(taskNumber);
        ui.botPrintTaskRemoved(task, taskList.getSize());
        storage.saveTasks(taskList);
    }
}
