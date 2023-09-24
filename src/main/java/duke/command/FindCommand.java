package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.task.DukeList;

/**
 * The FindCommand class represents a command for finding tasks that match a search query.
 *
 */
public class FindCommand extends Command {
    private String description;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param args The search query for finding tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

/**
 * Executes the FindCommand by searching for tasks that match the given query.
 *
 * @param dukelist The task list to search within.
 * @param storage  The storage object (not used in this command).
 * @return A message displaying the tasks that match the search query.
 * @throws DukeException If there is an error executing the command.
 */
@Override
public String execute(DukeList dukelist, Storage storage) throws DukeException {
    return dukelist.findTasks(this.description);

}
}