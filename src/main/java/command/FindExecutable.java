package command;
import java.util.ArrayList;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;
/**
 * Represents an execution call to find a particular keyword in the tasklist.
 */
public class FindExecutable implements Executable {
    private final String keyword;
    /**
     * Generates a new find command.
     * @param keyword the keyword to be searched.
     */
    public FindExecutable(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks that match the keyword if they exist.
     * @param list the list to search.
     * @param ui the interface to output to.
     * @return false, since the execution does not shut the bot down.
     * @throws FailureInExecuteException if we cannot find any tasks.
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        ArrayList<Task> res = list.findTasksMatching(keyword);
        if (res.isEmpty()) {
            throw new FailureInExecuteException("Could not find task");
        } else {
            StringBuilder ans = new StringBuilder("Here are the matching items.");
            for (int i = 0; i < res.size(); i++) {
                ans.append("\n");
                ans.append(i + 1);
                ans.append(". ");
                ans.append(res.get(i).toString());
            }
            ui.output(ans.toString());
        }
        return false;
    }
}
