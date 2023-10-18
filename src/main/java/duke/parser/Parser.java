package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;

/**
 * Represents a parser
 */
public class Parser {
    private static final String DATE_TIME_FORMAT = "dd/MM/yy HHmm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final String INVALID_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "If you need help with commands, please type 'help'!";
    private static final String INVALID_DEADLINE = "OOPS!!! Please enter a valid deadline - deadline return book /by "
            + DATE_TIME_FORMAT;
    private static final String INVALID_EVENT = "OOPS!!! Please enter a valid event - event read book /from "
            + DATE_TIME_FORMAT + " /to " + DATE_TIME_FORMAT;
    private static final String INVALID_FIND = "OOPS!!! Please enter a valid find query - find book";
    private static final String INVALID_DELETE = "OOPS!!! Please enter a valid delete query - delete 1";
    private static final String INVALID_MARK = "OOPS!!! Please enter a valid mark query - mark/unmark 1";

    /**
     * Parses the query and returns the corresponding command
     *
     * @param query the query
     * @return the corresponding command
     */
    public static Command parse(String query) {
        String[] split = query.split(" ", 2);
        Command command = null;
        String keyword = split[0];
        switch (keyword) {
        case "help":
            command = validateHelp(split);
            break;
        case "bye":
            command = validateExit(split);
            break;
        case "list":
            command = validateList(split);
            break;
        case "todo":
        case "deadline":
        case "event":
            command = validateTask(split);
            break;
        case "mark":
        case "unmark":
            command = validateMark(split);
            break;
        case "delete":
            command = validateDelete(split);
            break;
        case "find":
            command = validateFind(split);
            break;
        default:
            command = new IncorrectCommand(INVALID_COMMAND);
        }
        return command;
    }

    /**
     * Validates the find command
     *
     * @param split the split query
     * @return the corresponding command
     */
    private static Command validateFind(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("OOPS! You are missing a keyword\n" + INVALID_FIND);
        }
        if (split.length > 2) {
            return new IncorrectCommand("OOPS! You have too many keywords\n" + INVALID_FIND);
        }
        return new FindCommand(split[1]);
    }

    /**
     * Validates the mark command
     *
     * @param split the split query
     * @return the corresponding command
     */
    private static Command validateMark(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("OOPS! You are missing a number\n" + INVALID_MARK);
        }

        if (split.length > 2) {
            return new IncorrectCommand("OOPS! You have entered too many numbers\n" + INVALID_MARK);
        }

        if (!isNumeric(split[1])) {
            return new IncorrectCommand("OOPS! You have entered a non-numeric item!\n" + INVALID_MARK);
        }

        int index = Integer.parseInt(split[1]);
        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("OOPS! You have entered a non-numeric item!\n"
                    + "Please enter a number greater than 0 - mark 1");
        }
        return new MarkCommand(index, split[0]);
    }

    /**
     * Validates the delete command
     *
     * @param split the split query
     * @return the corresponding command
     */
    public static Command validateDelete(String[] split) {

        if (split.length == 1) {
            return new IncorrectCommand("OOPS! You are missing a number\n" + INVALID_DELETE);
        }
        if (split.length > 2) {
            return new IncorrectCommand("OOPS! You have too many numbers\n" + INVALID_DELETE);
        }
        if (!isNumeric(split[1])) {
            return new IncorrectCommand("OOPS! You entered a non-numeric item!\n" + INVALID_DELETE);
        }

        int index = Integer.parseInt(split[1]);

        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }

        return new DeleteCommand(index);
    }

    /**
     * Validates the task command
     *
     * @param split the split query
     * @return the corresponding command
     */
    public static Command validateTask(String[] split) {
        IncorrectCommand split1 = null;
        if (split.length == 1) {
            split1 = new IncorrectCommand("OOPS! The description of a " + split[0]
                    + " cannot be empty.");
        } else if (split[1].isBlank()) {
            split1 = new IncorrectCommand("OOPS! The description of a " + split[0]
                    + " cannot be empty.");
        }
        if (split1 != null) {
            return split1;
        }
        if (split[0].equals("deadline")) {
            return getValidationForDeadline(split);
        } else if (split[0].equals("event")) {
            return getValidationForEvent(split);
        } else {
            // For Todo
            return new AddCommand(split[1]);
        }
    }

    /**
     * Validates the deadline command
     * @param split the split query
     * @return the corresponding command
     */
    private static Command getValidationForDeadline(String[] split) {
        if (!split[1].contains("/by")) {
            return new IncorrectCommand(INVALID_DEADLINE);
        }

        if (split[1].split("\\s+/by\\s+").length == 1) {
            return new IncorrectCommand("OOPS! You added a /by but did not include a deadline!.\n" + INVALID_DEADLINE);
        }

        String[] parts = split[1].split("\\s*/by\\s+");
        String taskName = parts[0];
        if (taskName.isBlank()) {
            return new IncorrectCommand("OOPS! The description of a deadline cannot be empty.\n" + INVALID_DEADLINE);
        }
        String deadline = parts[1];
        try {
            return new AddCommand(taskName, LocalDateTime.parse(deadline, DATE_TIME_FORMATTER));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("OOPS! Please enter the date & time in a valid format! (DD/MM/YY HHmm)");
        }
    }

    /**
     * Validates the event command
     * @param split the split query
     * @return the corresponding command
     */
    private static Command getValidationForEvent(String[] split) {
        if (!split[1].contains("/from") || !split[1].contains("/to")) {
            return new IncorrectCommand("OOPS! Your query is missing the prefixes /from or /to\n" + INVALID_EVENT);
        }
        int fromIndex = split[1].indexOf("/from");
        int toIndex = split[1].indexOf("/to");
        if (fromIndex > toIndex) {
            return new IncorrectCommand("OOPS! The /from prefix should come before the /to prefix.\n" + INVALID_EVENT);
        }
        if (split[1].split("\\s+/from\\s+").length == 1 || split[1].split("\\s+/to\\s+").length == 1) {
            return new IncorrectCommand("OOPS! You added a /from or /to but did not include a time!.\n"
                    + INVALID_EVENT);
        }
        String[] parts = split[1].split("\\s*/from\\s+|\\s*/to\\s+");
        String taskName = parts[0];
        if (taskName.isBlank()) {
            return new IncorrectCommand("OOPS! The description of an event cannot be empty.");
        }
        String from = parts[1];
        if (from.isEmpty()) {
            return new IncorrectCommand("OOPS! You added a /from but did not include a time!.\n" + INVALID_EVENT);
        }
        String to = parts[2];
        if (to.isEmpty()) {
            return new IncorrectCommand("OOPS! You added a /to but did not include a time!.\n" + INVALID_EVENT);
        }
        return getValidationForDeadlineTiming(from, to, taskName);
    }

    /**
     * Validates the deadline timing
     * @param from the start time
     * @param to the end time
     * @param taskName the task name
     * @return the corresponding command
     */
    private static Command getValidationForDeadlineTiming(String from, String to, String taskName) {
        try {
            // Check if the date is valid
            LocalDateTime fromDate = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
            LocalDateTime toDate = LocalDateTime.parse(to, DATE_TIME_FORMATTER);
            if (fromDate.isAfter(toDate) || fromDate.isEqual(toDate)) {
                return new IncorrectCommand("OOPS! The start date cannot be after the end date!");
            }
            return new AddCommand(taskName, fromDate, toDate);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
        }
    }

    /**
     * Validates the list command
     *
     * @param split the split query
     * @return the corresponding command
     */
    public static Command validateList(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("OOPS! Please enter a valid command - list");
        }
        return new ListCommand();
    }

    /**
     * Validates the exit command
     *
     * @param split the split query
     * @return the corresponding command
     */
    public static Command validateExit(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("OOPS! Please enter a valid command - bye");
        }
        return new ExitCommand();
    }

    /**
     * Validates the help command
     *
     * @param split the split query
     * @return the corresponding command
     */
    public static Command validateHelp(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("OOPS! Please enter a valid command - help");
        }
        return new HelpCommand();
    }

    /**
     * Checks if the string is numeric
     *
     * @param str the string
     * @return true if the string is numeric
     */
    static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
