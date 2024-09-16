package duke;

import duke.command.MarkCommand;

/**
 * The MarkParser class is responsible for parsing user input
 * and creating MarkCommand objects to unmark tasks in a list.
 */
public class MarkParser {
    /**
     * Parses the user input and creates an MarkCommand.
     *
     * @param arguments The user input containing the index of the task to mark.
     * @return A MarkCommand object that represents the mark command.
     * @throws DukeException.MarkException If the input is empty or cannot be parsed.
     */
    public static Command parseMarkCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException.MarkException();
        }
        int indexToMark = Integer.parseInt(arguments) - 1;
        return new MarkCommand(indexToMark);
    }
}
