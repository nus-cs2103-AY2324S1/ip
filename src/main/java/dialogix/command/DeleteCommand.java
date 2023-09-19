package dialogix.command;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;
import dialogix.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the index (1-based) of the task to be deleted.
     *
     * @param taskIndex The index (1-based) of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand by removing a task from the task list and saving the updated list.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        isDeleteValid(tasks.size());
        tasks.addToStack();
        Task deletedTask = tasks.get(taskIndex);
        tasks.delete(taskIndex);
        ui.printDeleteSuccessMessage(deletedTask, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    /**
     * Validates whether the task deletion is valid based on the task list size.
     *
     * @param size The size of the task list.
     * @throws DialogixException If the index is out of bounds.
     */
    private void isDeleteValid(int size) throws DialogixException {
        if (taskIndex < 0 || taskIndex >= size) {
            throw new DialogixException("Your task index should not be less than 0 or \"\n"
                    + "greater than the length of your current list.");
        }
    }
}
