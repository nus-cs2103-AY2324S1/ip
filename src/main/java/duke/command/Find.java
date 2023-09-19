package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Represents a command to search and display tasks that match a given search query.
 * This class extends the base Command class and provides functionality to execute the search.
 */
public class Find extends Command {

    /**
     * Constructs a Find command with the specified search query.
     *
     * @param str The search query to find matching tasks.
     */
    public Find(String str) {
        super(str);
    }

    /**
     * Executes the Find command by searching and displaying tasks that match the search query.
     *
     * @param lst The task list containing tasks to be searched.
     * @param ui The user interface for displaying results.
     * @param storage The storage for tasks (not used in this context).
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList lst, UI ui, Storage storage) throws DukeException {
        if (str.isEmpty()) {
            throw new DukeException("Please enter a search query.");
        }
        return ui.showMatch(lst.find(str));
    }
}
