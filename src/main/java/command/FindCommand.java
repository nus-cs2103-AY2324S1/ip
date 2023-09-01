package command;
import java.io.IOException;
import java.util.ArrayList;
import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;

public class FindCommand implements Commandable {
    private String keyword;

    public void setSearch(String keyword) {
        this.keyword = keyword;
    }

    public boolean execute(TaskList list, UserInterface ui) throws FailureInExecuteException {
        ArrayList<Task> res = list.findTasksMatching(keyword);
        if (res.isEmpty()) {
            ui.output("Could not find task");
        } else {
            String ans = "Here are the matching items.";
            for (int i = 0; i < res.size(); i++) {
                ans += "\n" + (i + 1) + ". " + res.get(i).toString();
            }
            ui.output(ans);
        }
        return false;
    }
}