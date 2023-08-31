package duke.util;

import java.util.HashMap;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.tasks.TaskType;



/**
 * Provides functionality to parse user input into commands that the application can understand.
 */
public class Parser {

    /**
     * Parses the input string into a Command.
     *
     * @param input The raw input string from the user.
     * @return A Command that corresponds to the input instruction.
     * @throws DukeException If the input does not match any command or is invalid.
     * @throws EmptyTaskException If the input is missing essential information.
     * @throws InvalidDateTimeException If the date or time format is invalid.
     */
    public static Command parse(String input) throws DukeException, EmptyTaskException, InvalidDateTimeException {
        if (input.isEmpty()) {
            throw new DukeException();
        }

        String[] pairSplit = input.split("/");

        Pair commandPair = parsePair(pairSplit[0]);
        String command = commandPair.getKey();
        String argument = commandPair.getValue();

        HashMap<String, String> map = new HashMap<>();

        for (int i = 1; i < pairSplit.length; i++) {
            Pair temp = parsePair(pairSplit[i]);
            map.put(temp.getKey(), temp.getValue());
        }
        int index;
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            index = parseInt(argument);
            return new MarkCommand(index);
        case "unmark":
            index = parseInt(argument);
            return new UnmarkCommand(index);
        case "delete":
            index = parseInt(argument);
            return new DeleteCommand(index);
        case "todo":
            if (argument.isEmpty()) {
                throw new EmptyTaskException(TaskType.TODO, "description");
            }
            return new AddTodoCommand(argument);
        case "deadline":
            if (argument.isEmpty()) {
                throw new EmptyTaskException(TaskType.TODO, "description");
            }
            String by = map.getOrDefault("by", "");
            if (by.isEmpty()) {
                throw new EmptyTaskException(TaskType.DEADLINE, "by");
            }
            return new AddDeadlineCommand(argument, DateParser.transformDateTimeFormat(by));
        case "event":
            if (argument.isEmpty()) {
                throw new EmptyTaskException(TaskType.TODO, "description");
            }
            String from = map.getOrDefault("from", "");
            if (from.isEmpty()) {
                throw new EmptyTaskException(TaskType.EVENT, "from");
            }
            String to = map.getOrDefault("to", "");
            if (to.isEmpty()) {
                throw new EmptyTaskException(TaskType.EVENT, "to");
            }
            return new AddEventCommand(argument,
                    DateParser.transformDateTimeFormat(from),
                    DateParser.transformDateTimeFormat(to));
        case "find":
            return new FindCommand(argument);
        default:
            throw new DukeException();
        }
    }

    /**
     * Parses a string to extract a pair of values.
     *
     * @param input The string to parse.
     * @return A Pair where the key is the first word of the input, and the value is the rest of the string.
     * @throws DukeException If the input string is empty.
     */
    private static Pair parsePair(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException();
        }
        String[] parts = input.split("\\s+", 2);

        String firstWord = parts[0];
        String restOfString = parts.length > 1 ? parts[1] : "";
        return new Pair(firstWord.trim(), restOfString.trim());
    }

    /**
     * Converts a string to an integer.
     *
     * @param argument The string to parse.
     * @return The integer representation of the string.
     * @throws DukeException If the string cannot be parsed into an integer.
     */
    private static int parseInt(String argument) throws DukeException {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new DukeException();
        }
    }
}

