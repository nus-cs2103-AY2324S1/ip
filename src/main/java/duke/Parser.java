package duke;

import duke.command.*;
import duke.task.TaskType;

/**
 * The Parser class is responsible for parsing user input and generating the corresponding commands.
 *
 * @author selwyn
 */
public class Parser {
    /**
     * Parses the user input to generate the appropriate command.
     *
     * @param userInput The input provided by the user.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the input cannot be parsed into a valid command.
     */
    public Command parseCommand(String userInput) throws DukeException {
        String userCommand, args;
        String[] parsedCommand = userInput.split(" ", 2);
        userCommand = parsedCommand[0];
        args = parsedCommand.length > 1 ? parsedCommand[1] : "";

        switch (userCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand(TaskType.TODO, args);
        case "deadline":
            return new AddCommand(TaskType.DEADLINE, args);
        case "event":
            return new AddCommand(TaskType.EVENT, args);
        case "mark":
            return new MarkCommand(args, true);
        case "unmark":
            return new MarkCommand(args, false);
        case "delete":
            return new DeleteCommand(args);
        case "find":
            return new FindCommand(args);
        default:
            throw new DukeException("I don't understand what you are saying!\n"
                    + "Available commands are list, todo, deadline, event, mark, unmark, delete, bye.");
        }
    }
}