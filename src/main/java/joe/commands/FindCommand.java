package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.exceptions.JoeException;

/**
 * Represents a command to find all tasks containing the search string.
 */
public class FindCommand extends Command {
    public static final int DESC = 0;
    public static final int DESC_MATCH_CASE = 1;
    public static final int ALL = 2;
    private final String searchString;
    private final int searchType;

    /**
     * Constructs a FindCommand with the string to be searched for.
     *
     * @param searchString The string to be searched for.
     * @param searchType   The type of search
     */
    public FindCommand(String searchString, int searchType) {
        this.searchString = searchString;
        this.searchType = searchType;
    }

    /**
     * Executes the command to display the list of tasks containing the search string.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws JoeException {
        TaskList results;
        switch (searchType) {
        case DESC:
            results = tasks.findByDesc(searchString, false);
            break;
        case DESC_MATCH_CASE:
            results = tasks.findByDesc(searchString, true);
            break;
        case ALL:
            results = tasks.findAll(searchString);
            break;
        default:
            throw new JoeException("Bad search type!");
        }
        return "Here are the find results:\n" + results.toString();
    }
}
