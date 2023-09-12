package command;

import common.Message;
import exceptions.InvalidIndexException;
import storage.Storage;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to mark a task as done in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdSetPriority extends Command {
    private int index;
    private int priority;

    /**
     * Constructs a CmdMark object with the index of the task to be marked as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public CmdSetPriority(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }

    /**
     * Executes the command to mark a task as done, displays the marked task,
     * and updates the storage.
     *
     * @param taskList The task list in which the task should be marked as done.
     * @return response to the user.
     * @throws InvalidIndexException if the index is out of range of the task list.
     */
    @Override
    public String execute(TaskList taskList) throws InvalidIndexException {

        if (this.index < 0 || this.index > taskList.size() - 1) {
            throw new InvalidIndexException();
        }

        taskList.setPriority(index, priority);
        assert taskList.getTask(index).getPriority() == priority : "Priority does not match";
        Storage.writeToFile(taskList);
        String message = String.format(Message.SET_PRIORITY, priority);
        return StringUtility.joinLines(message, taskList.getTask(index).toString());
    }

}
