package tasket.command;

import static tasket.commons.Messages.MESSAGE_EMPTY_INDEX;
import static tasket.commons.Messages.MESSAGE_NOT_NUMBER;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for mark command.
 */
public class MarkCommand extends Command {

    /**
     * The constructor for mark command.
     *
     * @param index The index for the task to be deleted.
     */
    public MarkCommand(String index) {
        super(index);
    }

    /**
     * Marks the selected task as done.
     * Rewrite the save file after marking the task.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return The description of marked task.
     * @throws TasketException If the index is not a number, less than 0 or exceed the task list size.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        if (commandDescription.isEmpty()) {
            throw new TasketException(MESSAGE_EMPTY_INDEX);
        }

        try {
            int i = Integer.parseInt(commandDescription);
            taskList.checkIndexRange(i);

            taskList.mark(i - 1);
            storage.rewriteSaveFile(taskList);
            return ui.showMarkedTask(taskList.getTaskString(i - 1));
        } catch (NumberFormatException e) {
            throw new TasketException(MESSAGE_NOT_NUMBER);
        }
    }
}
