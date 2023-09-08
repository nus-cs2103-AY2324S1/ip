package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.parser.Validate;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks containing a specified keyword.
 */
public class FindCommand extends Command {
    private final String searchKeyword;

    /**
     * Constructs a FindCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public FindCommand(Params params) throws DukeException {
        super("find <search> | f <search>");
        this.searchKeyword = params.getArgumentIfSet("search", usageText);
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
