package command;

import task.Task;
import task.TaskList;
import ui.Ui;

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
     * @param ui       The user interface for displaying result of the search.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task[] filteredList = taskList.search(keyword);
        ui.print("Here are the matching tasks in your list:");
        for (int i = 1; i < filteredList.length + 1; i++) {
            Task task = filteredList[i - 1];
            ui.print(String.format("%d. %s", i, task.toString()));
        }
    }

}
