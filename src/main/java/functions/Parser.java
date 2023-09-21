package functions;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ErrorCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SearchCommand;
import commands.UnmarkCommand;
import utilities.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing user input and generating appropriate commands.
 */
public class Parser {

    /**
     * Enumeration representing different date and time formats for parsing.
     */
    enum DateTimeFormat {
        FORMAT1("yyyy-MM-dd HH:mm"),
        FORMAT2("dd.MM.yyyy HH:mm"),
        FORMAT3("MM/dd/yyyy HH:mm"),
        FORMAT4("yyyy-MM-dd hh:mm a"),
        FORMAT5("dd.MM.yyyy hh:mm a"),
        FORMAT6("MM/dd/yyyy hh:mm a"),
        FORMAT7("yyyy-MM-dd HHmm"),
        FORMAT8("dd.MM.yyyy HHmm"),
        FORMAT9("MM/dd/yyyy HHmm");

        private final DateTimeFormatter formatter;

        DateTimeFormat (String pattern) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }

    /**
     * Parses a date string using various date and time formats.
     *
     * @param dateString The date string to be parsed.
     * @return A LocalDateTime object if parsing is successful, otherwise null.
     */
    public static LocalDateTime parseDate(String dateString) {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                return LocalDateTime.parse(dateString, format.getFormatter());
            } catch (DateTimeParseException e) {
                // Try the next format
            }
        }
        // None of the formats matched
        return null;
    }

    /**
     * Parses the user input and generates the corresponding command object.
     *
     * @param input The user input string.
     * @return A Command object representing the parsed command.
     */
    public static Command parse(String input) throws IndexOutOfBoundsException {
        try {
            String[] words = input.split(" ");
            String first = words[0];
            switch (first) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "delete":
                    int s = checkIndex(words);
                    return new DeleteCommand(s);
                case "mark":
                    int sMark = checkIndex(words);
                    return new MarkCommand(sMark);
                case "unmark":
                    int sUnmark = checkIndex(words);
                    return new UnmarkCommand(sUnmark);
                case "find":
                    return handleSearch(input);
                case "todo":
                    return handleToDo(input);
                case "fixed":
                    return handleFixed(input);
                case "deadline":
                    return handleDeadline(input);
                case "event":
                    return handleEvent(input);
                default:
                    throw new CR7InvalidCommandException(Messages.WRONG_COMMAND);
            }
        } catch (CR7Exception e) {
            return new ErrorCommand(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand(Messages.INVALID_NUMBER);
        }
    }

    private static int checkIndex(String[] words) throws CR7Exception{
        try {
            return Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
    }

    private static Command handleToDo(String input) throws CR7Exception {
        if (input.length() <= 5) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        String todoDesc = input.substring(5);
        if (todoDesc.isBlank()) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        return new AddCommand(todoDesc);
    }

    private static Command handleSearch(String input) throws CR7Exception {
        if (input.length() <= 5) {
            throw new CR7EmptyInputException(Messages.EMPTY_SEARCH);
        }
        String desc = input.substring(5);
        if (desc.isBlank()) {
            throw new CR7EmptyInputException(Messages.EMPTY_SEARCH);
        }
        return new SearchCommand(desc);
    }

    private static Command handleFixed(String input) throws CR7Exception {
        if (input.length() <= 6) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        int y = input.indexOf("/for ");
        if (y == -1 || y <= 8) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
        String desc = input.substring(6, y - 1);
        String duration = input.substring(y + 5);
        if (desc.isBlank() || duration.isBlank()) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        return new AddCommand(desc, duration);
    }

    private static Command handleDeadline(String input) throws CR7Exception {
        if (input.length() <= 9) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        int z = input.indexOf("/by ");
        if (z == -1 || z <= 11) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
        String desc = input.substring(9, z - 1);
        if (desc.isBlank()) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        String d = input.substring(z + 4);
        LocalDateTime date = parseDate(d);
        if (date == null) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
        return new AddCommand(desc, date);
    }

    public static Command handleEvent(String input) throws CR7Exception {
        if (input.length() <= 6) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || fromIndex <= 8 || toIndex == -1 || toIndex < fromIndex) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
        String name = input.substring(6, input.indexOf("/") - 1);
        if (name.isBlank()) {
            throw new CR7EmptyInputException(Messages.EMPTY_TASK);
        }
        String g = input.substring(fromIndex + 6, toIndex).trim();
        String e = input.substring(toIndex + 4).trim();
        LocalDateTime start = parseDate(g);
        LocalDateTime end = parseDate(e);
        if (start == null || end == null) {
            throw new CR7InvalidInputException(Messages.WRONG_FORMAT);
        }
        return new AddCommand(name, start, end);
    }
}


