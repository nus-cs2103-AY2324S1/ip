package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui      The user interface to show messages to the user.
     * @param storage The storage to save the task list to.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.getSize() == 0) {
            ui.showNoMatchingTasksMessage();
        } else {
            ui.showMatchingTasksMessage(matchingTasks);
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
