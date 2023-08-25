package duke.command;

import duke.DukeException;

public class Parser {

    public static Command parseCommand(String rawCommand) throws DukeException {
        // Clean Raw Input
        rawCommand = rawCommand.trim().replaceAll(" +", " ");

        // Read Command
        String[] userInput = rawCommand.split(" ", 2);
        String command = userInput[0];
        String arguments = userInput.length == 2 ? userInput[1] : null;
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand(arguments);
        case "mark":
            return new MarkCommand(arguments);
        case "unmark":
            return new UnmarkCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new DukeException("Invalid Command: " + command + " , Please Try Again...");
        }
    }
}
