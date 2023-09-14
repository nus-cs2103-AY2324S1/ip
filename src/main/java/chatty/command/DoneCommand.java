package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the mark command that the user entered
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Contructor for exit command that will create a task object
     * @param i the index of the task the user wants to mark as done
     */
    public DoneCommand(int i) {
        super(false);
        this.index = i;
    }

    /**
     * Mark the indicated task as done
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that shows the user that the task has been mark done
     * @throws ChattyException when storage could not update the files
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
