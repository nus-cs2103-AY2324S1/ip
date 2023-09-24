package commands;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

/**
 * Represents the command to search for all tasks matching the keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes an FindCommand with the given keyword to search for.
     *
     * @param keyword The keyword to be matched with task description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching through the task list for tasks
     * that match the given keyword. Matches are accumulated and then displayed.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks to search through.
     * @throws DukeException If any issues arise during the search process.
     */
    @Override
    public String executeCommand(UI ui, Actions actionList) throws DukeException {
        StringBuilder matchedTasks = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < actionList.size(); i++) {
            Task task = actionList.getAction(i);
            if (task.getDescription().contains(keyword)) {
                ++counter;
                matchedTasks.append(" ").append(counter).append(". ").append(task).append("\n");
            }
        }
        if (counter == 0) {
            return " No Matches -> " + keyword;
        } else {
            return " Check out the matches:\n" + matchedTasks;
        }
    }
}
