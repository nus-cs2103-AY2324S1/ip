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
        super(null);
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
        StringBuilder response = new StringBuilder();

        try {
            Task deletedTask = tasks.remove(taskIndex - 1);
            storage.save(tasks.getList());

            // Build response message
            response.append("Task successfully deleted: ");
            response.append(deletedTask);

        } catch(Exception e) {
            throw new DukeException(e.getMessage());
        }

        return response.toString();
    }
}
