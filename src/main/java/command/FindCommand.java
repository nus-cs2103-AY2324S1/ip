package command;

import java.util.ArrayList;

import duke.Storage;
import task.Task;
import task.TaskList;

/**
 * Prints a filtered todo list according to the keyword in input
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    /** Keyword to filter the todo list */
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints a filtered TaskList which contains tasks whose contains the
     * given keyword
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param storage File path where the tasks are stored
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterTasks(this.keyword);
        TaskList filteredList = new TaskList(filteredTasks);
        if (filteredTasks.isEmpty()) {
            return "No matching tasks found";
        } else {
            return "Here are the matching tasks in your list:\n" + filteredList;
        }
    }
}
