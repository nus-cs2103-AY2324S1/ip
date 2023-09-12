package command;

import common.Message;
import storage.Storage;
import task.Task;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to add a task to the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdAddTask extends Command {
    private Task task;

    /**
     * Constructs a CmdAddTask object with the task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public CmdAddTask(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     *
     * @param taskList The task list to which the task should be added.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.addTask(task);
        Storage.writeToFile(taskList);

        return StringUtility.joinLines(Message.DELETE_TASK,
                task.toString(),
                String.format(Message.TASKLIST_STATUS, taskList.size()));
    }

}
