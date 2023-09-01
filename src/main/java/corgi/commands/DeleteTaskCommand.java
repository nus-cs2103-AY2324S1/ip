package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 * This command deletes a task at the specified index from the task list.
 */
public class DeleteTaskCommand extends Command{
    /**
     * The index of the task to be deleted.
     */
    private int targetIdx;

    /**
     * Initializes a new DeleteTaskCommand instance with the specified target index.
     *
     * @param targetIdx The index of the task to be deleted.
     */
    public DeleteTaskCommand(int targetIdx) {
        super(false, CommandType.DELETE);
        this.targetIdx = targetIdx;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * saving the updated list to storage, and displaying a message to the user indicating 
     * that the task has been deleted.
     * 
     * @param list The task list from which the task should be deleted.
     * @param ui The user interface for displaying feedback to the user.
     * @param storage The storage for saving and loading tasks (if applicable).
     * @throws CommandExecutionException If an error occurs during command execution.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage<Task> storage) throws CommandExecutionException {
        try {
            String targetTaskInfo = list.getTaskInfo(targetIdx);
            list.remove(targetIdx);
            storage.save(list);
            ui.showTaskDeleted(targetTaskInfo, list.size());
        } catch (TaskListIndexOutOfBoundsException e) {
            throw new CommandExecutionException("Invalid index provided!");
        }
    }
}
