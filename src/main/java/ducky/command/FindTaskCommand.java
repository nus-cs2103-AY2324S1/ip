package ducky.command;

import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

/**
 * Represents a command that finds tasks containing a given query.
 */
public class FindTaskCommand extends Command {

    private final String queryString;

    /**
     * Constructs a command to find tasks containing the specified query.
     * @param queryString Query to find tasks in task list.
     */
    public FindTaskCommand(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) {
        String result = taskList.getTaskQueryResult(this.queryString);
        ui.showMessagePerLine(result);
        return result;
    }
}
