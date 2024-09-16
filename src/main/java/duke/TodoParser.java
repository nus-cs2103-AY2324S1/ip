package duke;

import duke.command.AddCommand;

/**
 * The TodoParser class is responsible for parsing user input
 * and creating AddCommand objects for adding a new "Todo" task.
 */
public class TodoParser {
    /**
     * Parses the user input and creates an AddCommand for adding a new "Todo" task.
     *
     * @param arguments The description of the "Todo" task.
     * @return An AddCommand object that represents the add "Todo" task command.
     * @throws DukeException.ToDoException If the input is empty.
     */
    public static AddCommand parseTodoCommand(String arguments) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException.ToDoException();
        }
        return new AddCommand(AddCommand.TaskType.TODO, arguments);
    }
}
