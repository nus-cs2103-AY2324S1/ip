package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the mark command entered by the user to mark a task as done.
 * When the user provides a mark command, an instance of this class is created to
 * mark a task as done in the task list.
 */
public class DoneCommand extends Command {

    private final int index;

    /**
     * Constructor for the DoneCommand instance.
     * Initializes the instance with the index of the task the user wants to mark as done.
     *
     * @param i The index of the task the user wants to mark as done.
     */
    public DoneCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Executes the mark command to mark a task as done in the task list.
     * This method marks the specified task as done, updates the storage,
     * and returns a message confirming the task has been marked as done.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data.
     * @return A String indicating that the task has been marked as done successfully.
     * @throws ChattyException If storage encounters an issue while saving the task list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        taskList.markTask(index, true);

        try {
            storage.saveTask(taskList);
        } catch (Exception e) {
            throw new ChattyException("Cannot save chatty.task!");
        }
        return ui.showDone(index, taskList);
    }
}
