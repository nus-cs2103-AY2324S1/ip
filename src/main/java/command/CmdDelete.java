package command;

import common.Message;
import exceptions.InvalidIndexException;
import storage.Storage;
import task.Task;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to delete a task from the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdDelete extends Command {
    private int index;

    /**
     * Constructs a CmdDelete object with the index of the task to be deleted.
     *
     * @param index The index of the task to be deleted.
     */
    public CmdDelete(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list, displays
     * the deleted task, updates the task list size, and writes changes to
     * the storage.
     *
     * @param taskList The task list from which the task should be deleted.
     * @return response to the user.
     * @throws InvalidIndexException if the index is out of range of the task list.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidIndexException {

        if (this.index < 0 || this.index > taskList.size() - 1) {
            throw new InvalidIndexException();
        }

        Task deleted = taskList.deleteTask(index);
        Storage.writeToFile(taskList);

        return StringUtility.joinLines(Message.DELETE_TASK,
                deleted.toString(),
                String.format(Message.TASKLIST_STATUS, taskList.size()));

    }

}
