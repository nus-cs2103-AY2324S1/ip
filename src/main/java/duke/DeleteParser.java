package duke;

import duke.command.DeleteCommand;

/**
 * The DeleteParser class is responsible for parsing user input
 * and creating DeleteCommand objects to delete tasks from a list.
 */
public class DeleteParser {
    /**
     * Parses the user input and creates a DeleteCommand.
     *
     * @param arguments The user input containing the index of the task to delete.
     * @return A DeleteCommand object that represents the delete command.
     * @throws DukeException If the input is not a valid index or cannot be parsed.
     */
    public static Command parseDeleteCommand(String arguments) throws DukeException {
        int indexToDelete = Integer.parseInt(arguments) - 1;
        return new DeleteCommand(indexToDelete);
    }
}
