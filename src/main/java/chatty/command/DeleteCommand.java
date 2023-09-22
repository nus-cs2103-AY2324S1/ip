package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the delete command entered by the user.
 * When the user provides a delete command, an instance of this class is created to
 * delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;


    /**
     * Constructor for the delete command instance.
     * Initializes the instance with the index of the task the user wants to delete.
     *
     * @param i The index of the task the user wants to delete.
     */
    public DeleteCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Executes the delete command to remove a task from the task list.
     * This method deletes the specified task from the task list, updates the storage,
     * and returns a message confirming the task deletion.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data.
     * @return A String indicating that the task has been deleted successfully.
     * @throws ChattyException If storage encounters an issue while saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showDelete(index, taskList);
    }
}
