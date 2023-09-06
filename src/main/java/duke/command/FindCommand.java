package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to find tasks in the task list based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a find command with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param tasks   The task list to search for tasks.
     * @param storage The storage to save the task list to.
     * @return The response to the find command.
     */
    public String execute(TaskList tasks, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.getSize() == 0) {
            return "There are no matching tasks in your list!";
        } else {
            StringBuilder tasksString = new StringBuilder();
            for (int i = 0; i < matchingTasks.getSize(); i++) {
                tasksString.append(String.format("  %d. %s\n", i + 1, matchingTasks.getTask(i).toString()));
            }
            return String.format(" Here are the matching tasks in your list:\n%s", tasksString);
        }
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
