package command;

import common.Message;
import task.Task;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to list all tasks in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdList extends Command {

    /**
     * Executes the command to list all tasks and their indices in the task list.
     *
     * @param taskList The task list to retrieve tasks from.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        String[] lines = new String[taskList.size() + 1];
        lines[0] = Message.LIST_TASK;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            lines[i + 1] = (i + 1) + ". " + task.toString();
        }

        return StringUtility.joinLinesArray(lines);
    }

}
