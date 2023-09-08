package duke.parser;

import duke.command.*;
import duke.error.DukeException;
import duke.lib.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        if (paramArray.length > 1) {
            for (int i = 1; i < paramArray.length; i++) {
                String[] keyValue = paramArray[i].trim().split("\\s+", 2);

                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1].trim();

                    paramsMap.setParam(key, value);
                } else {
                    throwException("Invalid parameter format", "command /param1 ... /param2 ... /param3 ...");
                }
            }
        }
        return paramsMap;
    }

    /**
     * Throws a DukeException with a formatted error message including usage information.
     *
     * @param message   The error message to display.
     * @param usageText The usage information for the command.
     * @throws DukeException The exception with the formatted error message.
     */
    private static void throwException(String message, String usageText) throws DukeException {
        throw new DukeException(String.format("%s\n\n\tUsage: %s", message, usageText));
    }
}
