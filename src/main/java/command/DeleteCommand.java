package command;

import exception.DukeException;
import task.Task;
import task.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * Represents a command to delete a specific task.
 */
public class DeleteCommand extends Command {

    private final int taskIndex;

    /**
     * Initializes a DeleteCommand with the specified task index to be deleted.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        super(null);  // Removed unnecessary `null` argument.
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DeleteCommand command to delete a specific task from the task list.
     *
     * @param tasks   The {@link TaskList} containing the list of tasks.
     * @param ui      The {@link Ui} for user interface interactions.
     * @param storage The {@link Storage} for loading and saving tasks.
     * @return A string representation of the deletion confirmation message.
     * @throws DukeException If there is an error in deleting the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.remove(taskIndex - 1);
            storage.save(tasks.getList());
            return buildDeletionResponseMessage(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task index.");
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Builds the response message for a successful task deletion.
     *
     * @param deletedTask The task that has been deleted.
     * @return The response message string.
     */
    private String buildDeletionResponseMessage(Task deletedTask) {
        return "Task successfully deleted: " + deletedTask;
    }
}