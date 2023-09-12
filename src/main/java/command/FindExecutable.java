package command;
import java.util.ArrayList;

import duke.TaskList;
import duke.UserInterface;
import task.Task;

/**
 * A command that asks to find tasks that match the keyword.
 */
public class FindExecutable implements Executable {
    private String keyword;

    /**
     * Sets the keyword to search for.
     * @param keyword the string passed for searching.
     */
    public void setSearch(String keyword) {
        assert (!keyword.isBlank()); // Parser should pass a valid keyword only.
        this.keyword = keyword;
    }

    /**
     * for the FindExecutable, this searches the list for the relevant tasks that include the keyword,
     * if any. Thereafter, it will produce any results obtained to the ui.
     * @param list the list to be searched.
     * @param ui the interface to print out the output.
     * @return False, since this is a non-terminating operation.
     */
    @Override
    public boolean execute(TaskList list, UserInterface ui) {
        ArrayList<Task> res = list.findTasksMatching(keyword);
        if (res.isEmpty()) {
            ui.output("Could not find task");
        } else {
            StringBuilder ans = new StringBuilder("Here are the matching items.");
            for (int i = 0; i < res.size(); i++) {
                ans.append("\n").append(i + 1).append(". ").append(res.get(i).toString());
            }
            ui.output(ans.toString());
        }
        return false;
    }
}
