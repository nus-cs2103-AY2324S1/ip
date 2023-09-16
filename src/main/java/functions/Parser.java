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
                LocalDateTime d = LocalDateTime.parse(dateString, format.getFormatter());
                return d;
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
    public static Command parse(String input) {
        assert input != null && input.length() == 0 : "String should not be null or empty";
        String[] words = input.split(" ");
        String first = words[0];

        switch (first) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "delete":
                int s = Integer.valueOf(words[1]);
                return new DeleteCommand(s);

            case "mark":
                int sMark = Integer.valueOf(words[1]);
                return new MarkCommand(sMark);

            case "unmark":
                int sUnmark = Integer.valueOf(words[1]);
                return new UnmarkCommand(sUnmark);

            case "find":
                String search = words[1];
                return new SearchCommand(search);

            case "todo":
                if (input.length() <= 5) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                }
                String todoDesc = input.substring(5);
                return new AddCommand(todoDesc);


            case "fixed":
                if (input.length() <= 6) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                }
                int y = input.indexOf("/for ");
                if (y == -1) {
                    return new ErrorCommand("Please enter the duration of the task in the correct format.\n");
                }
                String fixedDesc = input.substring(6, y - 1);
                String duration = input.substring(y + 5);
                return new AddCommand(fixedDesc, duration);


            case "deadline":
                if (input.length() <= 9) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                }
                int z = input.indexOf("/by ");
                if (z == -1) {
                    return new ErrorCommand("Please enter the deadline of the task in the correct format.\n");
                }
                String desc = input.substring(9, z - 1);
                String d = input.substring(z + 4);
                LocalDateTime date = parseDate(d);
                if (date == null) {
                    return new ErrorCommand("PLease enter the date and time in the correct format. \n");
                }
                return new AddCommand(desc, date);


            case "event":
                if (input.length() <= 6) {
                    return new ErrorCommand("The description of a task cannot be empty.\n");
                }
                int fromIndex = input.indexOf("/from ");
                int toIndex = input.indexOf("/to ");
                if (fromIndex == -1 || toIndex == -1) {
                    return new ErrorCommand("Please enter the start and end time of the task in the correct format.\n");
                }
                String name = input.substring(6, input.indexOf("/") - 1);
                String g = input.substring(fromIndex + 6, toIndex).trim();
                String e = input.substring(toIndex + 4).trim();
                LocalDateTime start = parseDate(g);
                LocalDateTime end = parseDate(e);
                if (start == null || end == null) {
                    return new ErrorCommand("PLease enter the date and time in the correct format. \n");
                }
                return new AddCommand(name, start, end);


            default:
                return new ErrorCommand("I'm sorry, but I don't know what that means :(\n");
        }
    }
}


