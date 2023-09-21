package command;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import exceptions.EndBeforeStartException;
import exceptions.InvalidDateTimeFormat;
import exceptions.InvalidIndexException;
import exceptions.InvalidPriorityException;
import exceptions.MissingDescriptionException;
import exceptions.ThorndikeException;
import task.Deadline;
import task.Event;
import task.Todo;
import utility.DateTimeParser;
import utility.StringUtility;

/**
 * Utility class responsible for parsing user input and generating corresponding
 * Command objects.
 *
 * @author Ho Khee Wei
 */
public abstract class CommandParser {

    /**
     * Parses the user input to create a corresponding Command object.
     *
     * @param input User input string to be parsed.
     * @return A Command object corresponding to the parsed input.
     * @throws ThorndikeException If there are errors in parsing or the input is invalid.
     */
    public static Command parse(String input) throws ThorndikeException {
        String command = input.split(" ")[0];
        CommandKeyword commandKeyword = CommandKeyword.of(command);
        String[] argList = input.split(" /");
        String description = StringUtility.removeFirstWord(input.split(" /")[0]);
        Map<String, String> arguments = new HashMap<>();

        for (String arg : argList) {
            String option = StringUtility.getFirstWord(arg);
            String value = StringUtility.removeFirstWord(arg);

            if (!option.equals(command)) {
                arguments.put(option, value);
            } else {
                arguments.put("command", command);
                arguments.put("description", value);
            }
        }

        switch (commandKeyword) {

        case TODO:
            return parseTodo(arguments);

        case DEADLINE:
            return parseDeadline(arguments);

        case EVENT:
            return parseEvent(arguments);

        case EXIT:
            return new CmdBye();

        case DELETE:
            return new CmdDelete(parseIndex(description));

        case FIND:
            return new CmdFind(description);

        case HELP:
            return new CmdHelp(CommandKeyword.of(description));

        case LIST:
            return new CmdList();

        case MARK:
            return new CmdMark(parseIndex(description));

        case UNMARK:
            return new CmdUnmark(parseIndex(description));

        case PRIORITY:
            return new CmdSetPriority(parseIndex(description), parsePriority(arguments.get("set")));

        case INVALID:
            return new CmdHelp(CommandKeyword.INVALID);

        case TOGGLE:
            return new CmdToggle();

        default:
            return new CmdHelp(CommandKeyword.INVALID);
        }
    }

    /**
     * Parses an add todo task command.
     *
     * @param arguments Given to the command.
     * @return A Command that creates a todo task.
     * @throws ThorndikeException If there is missing description.
     */
    private static Command parseTodo(Map<String, String> arguments) throws ThorndikeException {
        String description = arguments.get("description");

        System.out.println(description);
        if (description.equals("")) {
            throw new MissingDescriptionException("todo");
        }

        Todo res = new Todo(description);
        if (arguments.containsKey("priority")) {
            int priority = parsePriority(arguments.get("priority"));
            res.setPriority(priority);
        }

        return new CmdAddTask(res);

    }

    /**
    * Parses an add deadline task command.
    *
    * @param arguments Given to the command.
    * @return A Command that creates a deadline task.
    * @throws ThorndikeException If there is missing description or wrong date, time format.
    */
    private static Command parseDeadline(Map<String, String> arguments) throws ThorndikeException {
        String description = arguments.get("description");
        if (description.equals("")) {
            throw new MissingDescriptionException("deadline");
        }

        LocalDateTime by = DateTimeParser.parse(arguments.get("by"));
        if (by == null) {
            throw new InvalidDateTimeFormat();
        }

        Deadline res = new Deadline(description, by);
        if (arguments.containsKey("priority")) {
            int priority = parsePriority(arguments.get("priority"));
            res.setPriority(priority);
        }

        return new CmdAddTask(res);

    }

    /**
    * Parses an add event task command.
    *
    * @param arguments Given to the command.
    * @return A Command that creates a event task.
    * @throws ThorndikeException If there is missing description or wrong date, time format.
    */
    private static Command parseEvent(Map<String, String> arguments) throws ThorndikeException {
        String description = arguments.get("description");
        if (description.equals("")) {
            throw new MissingDescriptionException("event");
        }

        LocalDateTime from = DateTimeParser.parse(arguments.get("from"));
        LocalDateTime to = DateTimeParser.parse(arguments.get("to"));

        if (from == null || to == null) {
            throw new InvalidDateTimeFormat();
        }

        Event res = new Event(description, from, to);
        if (arguments.containsKey("priority")) {
            int priority = parsePriority(arguments.get("priority"));
            res.setPriority(priority);
        }

        if (from.isAfter(to)) {
            throw new EndBeforeStartException();
        }

        return new CmdAddTask(res);
    }

    /**
     * Parses an index string and returns the corresponding task list index.
     *
     * @param index The string to be parsed.
     * @return The parsed task list index (zero-based).
     * @throws ThorndikeException If the index cannot be parsed or is invalid.
     */
    private static int parseIndex(String index) throws ThorndikeException {
        int idx = -1;

        try {
            idx = Integer.parseInt(index);
        } catch (Exception e) {
            throw new InvalidIndexException();
        }

        return idx - 1;
    }

    /**
     * Parses priority as string, checks if it's valid and returns it as an
     * integer.
     *
     * @param priority The string representing the priority to be parsed.
     * @return The parsed priority.
     * @throws ThorndikeException If the priority cannot be parsed or is invalid.
     */
    private static int parsePriority(String priority) throws ThorndikeException {
        int res = 0;

        try {
            res = Integer.parseInt(priority);
        } catch (Exception e) {
            throw new InvalidPriorityException();
        }

        if (res < 0 || res > 5) {
            throw new InvalidPriorityException();
        }

        return res;
    }
}
