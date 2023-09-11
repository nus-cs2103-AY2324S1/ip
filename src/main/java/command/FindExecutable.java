package command;
import java.util.ArrayList;

import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;

/**
 * A command that asks to find tasks that match the keyword.
 */
public class FindExecutable implements Executable {
    private String keyword;

    public void setSearch(String keyword) {
        assert (!keyword.isBlank());
        this.keyword = keyword;
    }

    @Override
    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
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
