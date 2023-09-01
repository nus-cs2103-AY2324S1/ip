package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword in their descriptions.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Represents a command to find tasks containing a specific keyword in their descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand to search for tasks with descriptions containing the keyword,
     * and displays the matching tasks.
     *
     * @param tasks   The TaskList containing tasks.
     * @param ui      The Ui for user interface interactions.
     * @param storage The Storage for loading and saving tasks (not used in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since FindCommand is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}