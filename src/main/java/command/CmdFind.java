package command;

import common.Message;
import task.Task;
import task.TaskList;
import utility.StringUtility;

/**
 * Represents a command to find tasks matching a keyword in the task list.
 *
 * @author Ho Khee Wei
 */
public class CmdFind extends Command {
    private String keyword;

    /**
     * Constructs a CmdFind object with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public CmdFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command on the provided task list.
     *
     * @param taskList The task list to search within.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList taskList) {
        Task[] filteredList = taskList.search(keyword);
        String[] lines = new String[filteredList.length + 1];
        lines[0] = Message.FIND_TASK;

        for (int i = 0; i < filteredList.length; i++) {
            Task task = filteredList[i];
            lines[i + 1] = task.toString();
        }

        return StringUtility.joinLinesArray(lines);
    }

}
