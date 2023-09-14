package tasket.command;

import tasket.storage.Storage;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.ui.Ui;

/**
 * The class for delete command.
 */
public class DeleteCommand extends Command {

    /**
     * The constructor for delete command.
     * @param index The index for the task to be deleted.
     */
    public DeleteCommand(String index) {
        super(index);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @throws TasketException If the index is not a number, less than 0 or exceed the task list size.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        if (commandDescription.isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            int i = Integer.parseInt(commandDescription);

            if (i <= 0) {
                throw new TasketException("The task index cannot be less than 1");
            } else if (i > taskList.size()) {
                throw new TasketException("The task index cannot exceed the list");
            }

            String deletedTaskString = taskList.remove(i - 1).toString();
            ui.showDeletedTask(deletedTaskString, taskList.size());
            storage.rewriteSaveFile(taskList);
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        }
    }
}
