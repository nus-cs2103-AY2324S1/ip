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

/**
 * The Action enum represents the various actions that can be performed by Duke.
 */
enum Action {
    BYE, LIST, FIND, EDIT_TASK, ADD_TASK, INVALID;

    public static Action parseCommand(String command) throws InvalidCommandException {

        if (checkEditAction(command)) {
            return EDIT_TASK;
        } else if (checkAddAction(command)) {
            return ADD_TASK;
        } else {
            Action[] values = Action.values();

            for (int i = 0; i < 3; i++) {
                if (command.toUpperCase().equals(values[i].toString())) {
                    return values[i];
                }
            }

            throw new InvalidCommandException("Invalid Command");
        }
    }


    private static boolean checkAddAction(String command) {
        final String[] addTypes = new String[]{"todo", "deadline", "event"};

        for (String type : addTypes) {
            if (command.equals(type)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkEditAction(String command) {
        final String[] editTypes = new String[]{"mark", "unmark", "delete"};

        for (String type : editTypes) {
            if (command.equals(type)) {
                return true;
            }
        }

        return false;
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
            "(?<deadline>[^\"]+) /from (" + DATE_PATTERN + ")" + " /to (" + DATE_PATTERN + ")");

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
            throw new InvalidCommandException("Invalid command.Command");
        }

        final String command = matcher.group("command");
        final String argument = matcher.group("arguments").trim();
        final boolean validIndex = argument.matches("-?\\d+");

        Action action = Action.parseCommand(command);

        switch (action) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case FIND:
            if (argument.equals("")) {
                throw new InvalidCommandException("Please enter keyword to find task");
            }

            return new FindCommand(argument);

        case EDIT_TASK:
            if (!validIndex) {
                throw new InvalidCommandException("Please input an integer to identify task");
            }

            return new EditCommand(command, Integer.parseInt(argument));

        case ADD_TASK:
            if (argument.equals("")) {
                throw new InvalidCommandException("Task description cannot be empty");
            }

            String[] taskArgs = parseTaskArguments(command, argument);
            return new AddCommand(command, taskArgs);

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

    /**
     * Returns the array of arguments required for task added. Task can be todo, deadline or event.
     *
     * @param taskType Type of task to be added to TaskList.
     * @param argument Input arguments from user.
     * @return The array of arguments required for task to be added.
     * @throws InvalidCommandException Error thrown when user did not input correct arguments to add task.
     */
    private static String[] parseTaskArguments(String taskType, String argument) throws InvalidCommandException {

        switch (taskType) {
        case "todo":
            return new String[]{argument};

        case "deadline":
            Matcher deadlineFormat = DEADLINE_FORMAT.matcher(argument);

            if (!deadlineFormat.matches()) {
                throw new InvalidCommandException("Invalid deadline command. "
                        + "Please include /by date in this format: yyyy-mm-dd HH:mm");
            }

            String deadLineDesc = deadlineFormat.group(1);
            String dateInput = deadlineFormat.group(2);

            LocalDateTime d = parseDateTime(dateInput);

            String byDate = reformatDateTime(d);

            return new String[]{deadLineDesc, byDate};

        case "event":
            Matcher eventFormat = EVENT_FORMAT.matcher(argument);

            if (!eventFormat.matches()) {
                throw new InvalidCommandException("Invalid event command. "
                        + "Please include /from and /to dates in this format: yyyy-mm-dd HH:mm");
            }

            String eventDesc = eventFormat.group(1);
            String fromDateInput = eventFormat.group(2);
            String toDateInput = eventFormat.group(3);

            LocalDateTime from = parseDateTime(fromDateInput);
            LocalDateTime to = parseDateTime(toDateInput);

            if (from.isAfter(to)) {
                throw new InvalidCommandException("/from date should be before /to date given");
            }

            String fromDate = reformatDateTime(from);
            String toDate = reformatDateTime(to);

            return new String[]{eventDesc, fromDate, toDate};

        default:
            throw new InvalidCommandException("Invalid task type");
        }
    }
}
