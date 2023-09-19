package duke;

import duke.command.SearchCommand;

/**
 * The SearchParser class is responsible for parsing user input
 * and creating SearchCommand objects for searching tasks.
 */
public class SearchParser {
    /**
     * Parses the user input and creates a SearchCommand for searching tasks.
     *
     * @param arguments The user input containing the keyword and optional filters.
     * @return A SearchCommand object that represents the search command.
     * @throws DukeException.SearchException If the input is empty.
     */
    public static Command parseSearchCommand(String arguments) throws DukeException {
        String[] searchArgs = arguments.split(" ", 2);
        String keyword = searchArgs[0];
        String filters = (searchArgs.length > 1) ? searchArgs[1] : "";

        if (arguments.isEmpty()) {
            throw new DukeException.SearchException();
        }
        return new SearchCommand(keyword, filters.toLowerCase());
    }
}
