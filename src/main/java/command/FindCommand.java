package command;
import java.util.ArrayList;
import duke.TaskList;
import duke.UserInterface;
import dukeexception.FailureInExecuteException;
import task.Task;

public class FindExecutable implements Executable {
    private final String keyword;
    /**
     * Generates a new find command.
     * @param keyword the keyword to be searched.
     */
    public FindExecutable(String keyword) {
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