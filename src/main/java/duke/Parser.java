package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.EditCommand;
import command.FindCommand;
import command.ListCommand;
import exception.DukeException;
import exception.InvalidCommandException;

enum Action {
    BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT, INVALID;

    public static Action parseCommand(String command) {
        for (Action action: Action.values()) {
            if (command.toUpperCase().equals(action.toString())) {
                return action;
            }
        }

        return Action.INVALID;
    }
}


/**
 * Parses user input.
 */
public class Parser {
    private static final Pattern BASIC_COMMAND = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
    private static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
    private static final Pattern DEADLINE_FORMAT = Pattern.compile(
            "(?<deadline>[^\"]+) /by (" + DATE_PATTERN + ")");

    private static final Pattern EVENT_FORMAT = Pattern.compile(
            "(?<deadline>[^\"]+) /from (" + DATE_PATTERN + ")"
                    + " /to (" + DATE_PATTERN + ")");

    /**
     * Parses user input and returns a command.Command. The command.Command can then be executed
     * to respond to the user input.
     *
     * @param input The user input.
     * @return A command.Command to be executed.
     * @throws DukeException If user input is invalid, Duke exception will be thrown.
     */
    public static Command parse(String input) throws DukeException {
        final Matcher matcher = BASIC_COMMAND.matcher(input.trim());

        if (!matcher.matches()) {
            throw new InvalidCommandException("Invalid command");
        }

        assert input.equals("") : "Command should not be empty after checks";

        final String command = matcher.group("command");
        final String argument = matcher.group("arguments").trim();
        final boolean validIndex = argument.matches("-?\\d+");
        final Action action = Action.parseCommand(command);

        switch(action) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case MARK:
            if (validIndex) {
                assert !argument.equals("") : "Argument should not be empty after checks";

                return new EditCommand("mark", Integer.parseInt(argument));
            } else {
                throw new InvalidCommandException("Please input an integer to identify task");
            }

        case UNMARK:
            if (validIndex) {
                return new EditCommand("unmark", Integer.parseInt(argument));
            } else {
                throw new InvalidCommandException("Please input an integer to identify task");
            }


        case DELETE:
            if (validIndex) {
                return new EditCommand("delete", Integer.parseInt(argument));
            } else {
                throw new InvalidCommandException("Please input an integer to identify task");
            }

        case FIND:
            if (argument.equals("")) {
                throw new InvalidCommandException("Please enter keyword to find task");
            }

            return new FindCommand(argument);


        case TODO:
            if (argument.equals("")) {
                throw new InvalidCommandException("ToDo description cannot be empty");
            }

            return new AddCommand("todo", new String[]{argument});

        case DEADLINE:
            Matcher deadlineFormat = DEADLINE_FORMAT.matcher(argument);
            if (argument.equals("")) {
                throw new InvalidCommandException("Deadline description cannot be empty");
            }

            if (deadlineFormat.matches()) {
                String desc = deadlineFormat.group(1);
                LocalDateTime d = parseDateTime(deadlineFormat.group(2));
                String byDate = reformatDateTime(d);

                assert byDate.matches(
                        "^[A-Z][a-z]{2}\\s\\d{1,2}\\s\\d{4}\\s\\d{1,2}\\.\\d{2}[APap][Mm]$");

                return new AddCommand("deadline", new String[]{desc, byDate});
            } else {
                throw new InvalidCommandException("Invalid deadline command. "
                        + "Please include /by date in this format: yyyy-mm-dd HH:mm");
            }

        case EVENT:
            Matcher eventFormat = EVENT_FORMAT.matcher(argument);
            if (argument.equals("")) {
                throw new InvalidCommandException("Event description cannot be empty");
            }

            if (eventFormat.matches()) {
                String desc = eventFormat.group(1);

                LocalDateTime from = parseDateTime(eventFormat.group(2));
                LocalDateTime to = parseDateTime(eventFormat.group(3));

                if (from.isAfter(to)) {
                    throw new InvalidCommandException("/from date should be before /to date given");
                }

                String fromDate = reformatDateTime(from);
                String toDate = reformatDateTime(to);

                assert fromDate.matches("^[A-Z][a-z]{2}\\s\\d{1,2}\\s\\d{4}\\s\\d{1,2}\\.\\d{2}[APap][Mm]$") &&
                        toDate.matches("^[A-Z][a-z]{2}\\s\\d{1,2}\\s\\d{4}\\s\\d{1,2}\\.\\d{2}[APap][Mm]$");

                return new AddCommand("event", new String[]{desc, fromDate, toDate});
            } else {
                throw new InvalidCommandException("Invalid event command. "
                        + "Please include /from and /to dates in this format: yyyy-mm-dd HH:mm");
            }

        default:
            throw new InvalidCommandException("Unknown/Invalid command given");
        }
    }

    /**
     * Return the string representing the dateTime input in MMM d yyyy h.mma format.
     *
     * @param input The dateTime string input from the user.
     * @return The string of the formatted DateTime input.
     */
    public static String reformatDateTime(LocalDateTime input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h.mma", Locale.ENGLISH);
        return input.format(formatter);
    }

    /**
     * Returns a LocalDateTime given by the user input.
     * Formats user input to be parsed as a LocalDateTime object.
     *
     * @param input The user date input.
     * @return The LocaleDateTime object.
     */
    public static LocalDateTime parseDateTime(String input) {
        String[] dateTime = input.split(" ", 2);
        String dateTimeFormat = dateTime[0] + "T" + dateTime[1] + ":00";
        return LocalDateTime.parse(dateTimeFormat);
    }
}
