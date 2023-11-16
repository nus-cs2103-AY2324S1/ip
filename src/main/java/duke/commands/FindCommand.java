package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class FindCommand extends Command {

    /**
     * Find tasks in task list matching the search term.
     * @param parsedInput the parsed command from the user.
     * @param message the default response.
     * @return String of tasks matching the search term.
     * @throws DukeException if no term is provided to search for.
     */
    @Override
    public String execute(String[] parsedInput, String message) throws DukeException {
        if (parsedInput.length <= 1) {
            throw new DukeException("Please provide a term to search for! (>_<)");
        } else {
            String searchTerm = parsedInput[1];
            message = TaskList.searchFor(searchTerm);
            assert message != null : "response should be given";
        }

        return message;
    }
}
