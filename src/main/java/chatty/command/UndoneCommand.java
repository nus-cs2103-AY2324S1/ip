package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the 'unmark' command entered by the user to mark a task as undone in the task list.
 * When the user provides an 'unmark' command with a task index, an instance of this class is created
 * to update the status of the specified task as undone.
 */
public class UndoneCommand extends Command {

    private final int index;

    /**
     * Constructor for the UndoneCommand instance.
     *
     * @param i The index of the task that the user wants to mark as undone.
     */
    public UndoneCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Executes the 'unmark' command to mark a task as undone in the task list.
     * This method updates the status of the specified task as undone,
     * saves the updated task list to storage (if required),
     * and returns a string indicating that the task has been marked as undone.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class for updating the stored task list (if required).
     * @return A String containing a message indicating that the task has been marked as undone.
     * @throws ChattyException If storage encounters an error while saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        taskList.markTask(index, false);

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showUndone(index, taskList);
    }
}
