package duke;

import duke.command.ByeCommand;
import duke.command.ListCommand;

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
            return SearchParser.parseSearchCommand(arguments);
        case "delete":
            return DeleteParser.parseDeleteCommand(arguments);
        case "todo":
            return TodoParser.parseTodoCommand(arguments);
        case "event":
            return EventParser.parseEventCommand(arguments);
        case "deadline":
            return DeadlineParser.parseDeadlineCommand(arguments);
        case "unmark":
            return UnmarkParser.parseUnmarkCommand(arguments);
        case "mark":
            return MarkParser.parseMarkCommand(arguments);
        default:
            throw new DukeException.EmptyException();
        }
    }
}
