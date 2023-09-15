package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SearchCommand;
import duke.command.UnmarkCommand;

// Solution below adapted and inspired by https://chat.openai.com/share/7f037351-3be6-4105-b138-77f68d428c84

/**
 * Parses user input to create and return the corresponding command object.
 */
public class Parser {

    /**
     * Parses the user input command and returns the corresponding command object.
     *
     * @param userInput The user's input command as a string.
     * @return A command object corresponding to the user's input.
     * @throws DukeException If there is an issue parsing the command or if the command is invalid.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] split = userInput.split(" ");
        String command = split[0];
        String arguments = userInput.replaceFirst(command, "").trim();

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "search":
            if (arguments.isEmpty()) {
                throw new DukeException.SearchException();
            }
            return new SearchCommand(arguments);
        case "delete":
            int indexToDelete = Integer.parseInt(arguments) - 1;
            return new DeleteCommand(indexToDelete);
        case "todo":
            if (arguments.isEmpty()) {
                throw new DukeException.ToDoException();
            }
            return new AddCommand(AddCommand.TaskType.TODO, arguments);
        case "event":
            if (arguments.isEmpty() || !arguments.contains("/from")) {
                throw new DukeException.EventException();
            }
            return EventParser.parseEventCommand(arguments);
        case "deadline":
            // Solution below adapted and inspired by https://chat.openai.com/share/b706b4df-ab30-4d0f-93eb-b85617616319
            if (arguments.isEmpty()) {
                throw new DukeException.DeadlineException();
            }
            return DeadlineParser.parseDeadlineCommand(arguments);
        case "unmark":
            if (arguments.isEmpty()) {
                throw new DukeException.UnmarkException();
            }
            int indexToUnmark = Integer.parseInt(arguments) - 1;
            return new UnmarkCommand(indexToUnmark);
        case "mark":
            if (arguments.isEmpty()) {
                throw new DukeException.MarkException();
            }
            int indexToMark = Integer.parseInt(arguments) - 1;
            return new MarkCommand(indexToMark);
        default:
            throw new DukeException.NoSuchItemException();
        }
    }
}
