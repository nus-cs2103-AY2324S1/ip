package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.Params;
import duke.command.UnmarkCommand;
import duke.error.DukeException;

/**
 * Parses user input commands and creates corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the full user input command and returns the corresponding Command object.
     *
     * @param fullCommand The full user input command.
     * @return The corresponding Command object.
     * @throws DukeException If there's an issue parsing the command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.split(" ", 2);

        String rootCmd = tokens[0];
        Params params = parseParams(tokens);

        switch (rootCmd) {
        case "list":
        case "ls":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "delete":
        case "rm":
            return new DeleteCommand(params);
        case "mark":
        case "m":
            return new MarkCommand(params);
        case "unmark":
        case "um":
            return new UnmarkCommand(params);
        case "find":
        case "f":
            return new FindCommand(params);
        case "todo":
        case "t":
            return new AddTodoCommand(params);
        case "deadline":
        case "d":
            return new AddDeadlineCommand(params);
        case "event":
        case "e":
            return new AddEventCommand(params);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Params parseParams(String[] params) throws DukeException {
        Params paramsMap = new Params();
        if (params.length == 1) {
            return paramsMap;
        }

        String[] paramArray = params[1].trim().split("\\s*/");

        String arg = paramArray[0].trim();
        paramsMap.setArgument(arg);

        if (paramArray.length == 1) {
            return paramsMap;
        }

        for (int i = 1; i < paramArray.length; i++) {
            String[] keyValue = paramArray[i].trim().split("\\s+", 2);
            if (keyValue.length != 2) {
                throw new DukeException(String.format("%s\n\n\tUsage: %s", "Invalid parameter format",
                    "command /param1 ... /param2 ... /param3 ..."));
            }

            String key = keyValue[0];
            String value = keyValue[1].trim();

            paramsMap.setParam(key, value);
        }
        return paramsMap;
    }
}
