package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the unmark command that the user entered
 */
public class UndoneCommand extends Command {

    private int index;

    /**
     * Contructor for unmark command
     * @param i the index of the task that the user want to mark as undone
     */
    public UndoneCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Mark the indicated task as undone
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that tells the user that the task has been made undone
     * @throws ChattyException when storage could not update the files
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
