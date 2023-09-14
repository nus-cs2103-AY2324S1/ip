package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the delete command that the user entered
 */
public class DeleteCommand extends Command {

    private int index;


    /**
     * Contructor for delete command
     * @param i the index of the task the user wants to delete
     */
    public DeleteCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Delete the task specified by the user
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that shows the user that the task has been deleted successfully
     * @throws ChattyException when storage could not update the files
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
