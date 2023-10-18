package commands;

import data.TaskList;
import data.exception.InvalidParamException;
import data.exception.StorageException;
import data.tasks.Task;
import storage.Storage;
import ui.UiMessage;

/**
 * The DeleteCommand class.
 * Handles deleting a {@link Task} from {@link TaskList}.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * The constructor method for a DeleteCommand class.
     * Takes in the index of the task to be deleted from {@link TaskList}.
     *
     * @param taskIndex The index of the task to be deleted.
     * @throws InvalidParamException Thrown when the taskIndex given
     *                               cannot be converted to a number.
     */
    public DeleteCommand(String taskIndex) throws InvalidParamException {
        try {
            this.taskIndex = Integer.parseInt(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidParamException(new String[] {
                "delete takes in a number.", "Try delete 1"
            });
        }
    }

    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage)
            throws InvalidParamException, StorageException {
        Task removedTask = tasks.delete(taskIndex - 1);

        // Write modified task list to file
        storage.update(tasks);
        return new UiMessage(new String[] {
            "Okie! I've deleted task " + taskIndex + ":",
            "  " + removedTask.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
