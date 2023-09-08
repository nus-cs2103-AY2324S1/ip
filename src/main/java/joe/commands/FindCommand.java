package joe.commands;

import joe.Storage;
import joe.TaskList;

/**
 * Represents a command to find all tasks containing the search string.
 */
public class FindCommand extends Command {
    private final String searchString;

    /**
     * Constructs a FindCommand with the string to be searched for.
     *
     * @param searchString The string to be searched for.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes the command to display the list of tasks containing the search string.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList results = tasks.find(searchString);
        return results.toString();
    }
}
