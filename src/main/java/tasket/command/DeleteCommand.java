package tasket.command;

import static tasket.commons.Messages.MESSAGE_EMPTY_INDEX;
import static tasket.commons.Messages.MESSAGE_NOT_NUMBER;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.StorageInterface;
import tasket.ui.Ui;

/**
 * The class for delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a delete command.
     *
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
     * @return The deleted message and number of tasks left.
     * @throws TasketException If the index is not a number, less than 0 or exceed the task list size.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, StorageInterface storage) throws TasketException {
        if (commandDescription.isEmpty()) {
            throw new TasketException(MESSAGE_EMPTY_INDEX);
        }

        try {
            int i = Integer.parseInt(commandDescription);
            taskList.checkIndexRange(i);

            String deletedTaskString = taskList.remove(i - 1).toString();
            storage.rewriteSaveFile(taskList);
            return ui.showDeletedTask(deletedTaskString, taskList.size());
        } catch (NumberFormatException e) {
            throw new TasketException(MESSAGE_NOT_NUMBER);
        }
    }
}
