package tasket.command;


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
            throw new TasketException("The task index cannot be empty");
        }

        try {
            int i = Integer.parseInt(commandDescription);

            if (i <= 0) {
                throw new TasketException("The task index cannot be less than 1");
            } else if (i > taskList.size()) {
                throw new TasketException("The task index cannot exceed the list");
            }

            taskList.unmark(i - 1);
            storage.rewriteSaveFile(taskList);

            return ui.showUnmarkedTask(taskList.getTaskString(i - 1));
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        }
    }
}
