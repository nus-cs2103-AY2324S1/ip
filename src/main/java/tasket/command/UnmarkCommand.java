package tasket.command;

import static tasket.commons.Messages.MESSAGE_EMPTY_INDEX;
import static tasket.commons.Messages.MESSAGE_NOT_NUMBER;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

/**
 * The class for unmark command.
 */
public class UnmarkCommand extends Command {

    /**
     * The constructor for unmark command.
     *
     * @param index The index for the task to be deleted.
     */
    public UnmarkCommand(String index) {
        super(index);
    }

    /**
     * Marks the task as undone.
     *
     * @param taskList The task list instance of duke.
     * @param ui The ui instance of duke.
     * @param storage The storage instance of duke.
     * @return The description of unmarked task.
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

            taskList.unmark(i - 1);
            storage.rewriteSaveFile(taskList);
            return ui.showUnmarkedTask(taskList.getTaskString(i - 1));
        } catch (NumberFormatException e) {
            throw new TasketException(MESSAGE_NOT_NUMBER);
        }
    }
}
