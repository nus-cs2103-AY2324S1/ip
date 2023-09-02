package chatbuddy.command;

import chatbuddy.TaskList;
import chatbuddy.storage.Storage;
import chatbuddy.ui.Ui;

import java.util.ArrayList;

/** FindCommand represents a command to find a list of tasks containing a keyword. */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    /**
     * Creates an instance of a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.getMatchingTasks(keyword);
        ArrayList<String> taskStrings = matchingTasks.getTaskStringsToPrint();
        ui.showTaskList(taskStrings, "Here are the matching tasks in your list:");
    }
}
