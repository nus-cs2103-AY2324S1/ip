package command;

import common.Message;
import storage.Storage;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to mark a task as not done in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdUnmark extends Command {
    private int index;

    /**
     * Constructs a CmdUnmark object with the index of the task to be marked as not
     * done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public CmdUnmark(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark a task as not done, displays the unmarked task,
     * and updates the storage.
     *
     * @param taskList The task list in which the task should be marked as not done.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.markNotDone(index);
        Storage.writeToFile(taskList);
        return (StringUtility.joinLines(Message.MARK_NOT_DONE, taskList.getTask(index).toString()));
    }

}
