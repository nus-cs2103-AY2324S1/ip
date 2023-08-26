package duke.command;

import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private String searchKeyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param searchKeyword The keyword to search for in task descriptions.
     */
    public FindCommand(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * Executes the FindCommand by searching for tasks matching the specified keyword and displaying the results.
     *
     * @param tasks   The TaskList containing tasks to search through.
     * @param ui      The UI for displaying messages to the user.
     * @param storage The Storage for saving task data.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.searchMatchingTasks(this.searchKeyword));
    }
}
