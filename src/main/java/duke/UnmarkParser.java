package duke;

import duke.command.UnmarkCommand;

/**
 * The UnmarkParser class is responsible for parsing user input
 * and creating UnmarkCommand objects to unmark tasks in a list.
 */
public class UnmarkParser {
    /**
     * Parses the user input and creates an UnmarkCommand.
     *
     * @param arguments The user input containing the index of the task to unmark.
     * @return An UnmarkCommand object that represents the unmark command.
     * @throws DukeException.UnmarkException If the input is empty or cannot be parsed.
     */
    public static Command parseUnmarkCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException.UnmarkException();
        }
        int indexToUnmark = Integer.parseInt(arguments) - 1;
        return new UnmarkCommand(indexToUnmark);
    }
}
