package command;

import java.util.Map;
import exceptions.InvalidDateTimeFormat;
import exceptions.InvalidIndexException;
import exceptions.MissingDescriptionException;
import exceptions.ThorndikeException;
import task.Deadline;
import task.Event;
import task.Todo;
import utility.DateTimeParser;
import java.time.LocalDateTime;
import java.util.HashMap;
import utility.StringUtility;

public abstract class CommandParser {

    /**
     * Parse the input given by user.
     * 
     * @param input User input
     * @return Map of options and values.
     */
    public static Command parse(String input) throws ThorndikeException {
        String command = input.split(" ")[0];
        String[] argList = input.split(" /");
        String description = StringUtility.removeFirstWord(input.split(" /")[0]);
        Map<String, String> arguments = new HashMap<>();
        for (String arg : argList) {
            String option = StringUtility.getFirstWord(arg);
            String value = StringUtility.removeFirstWord(arg);

            if (!option.equals(command)) {
                arguments.put("command", command);
                arguments.put("description", value);
                arguments.put(option, value);
            } else {

            }
        }

        if (command.equals("list")) {
            return new CmdList();
        }

        if (command.equals("bye")) {
            return new CmdBye();
        }

        if (command.equals("mark")) {
            return new CmdMark(parseIndex(description));
        }

        if (command.equals("unmark")) {
            return new CmdUnmark(parseIndex(description));
        }

        if (command.equals("todo")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("todo");
            }

            return new CmdAddTask(new Todo(description));
        }

        if (command.equals("deadline")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("deadline");
            }

            LocalDateTime by = DateTimeParser.parse(arguments.get("by"));
            if (by == null) {
                throw new InvalidDateTimeFormat();
            }

            return new CmdAddTask(new Deadline(description, by));
        }

        if (command.equals("event")) {
            if (description.equals("")) {
                throw new MissingDescriptionException("event");
            }

            LocalDateTime from = DateTimeParser.parse(arguments.get("from"));
            LocalDateTime to = DateTimeParser.parse(arguments.get("to"));
            if (from == null || to == null) {
                throw new InvalidDateTimeFormat();
            }

            return new CmdAddTask(new Event(description, from, to));
        }

        if (command.equals("find")) {
            return new CmdFind(description);
        }

        return null;
    }

    /**
     * Gets index from a string.
     * 
     * @param idx String to parse index.
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
}