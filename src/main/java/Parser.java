import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final Pattern BASIC_COMMAND = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");
    public static final String isoDatePattern = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
    public static final Pattern DEADLINE_FORMAT = Pattern.compile(
            "(?<deadline>[^\"]+) /by (" + isoDatePattern + ")");

    public static final Pattern EVENT_FORMAT = Pattern.compile(
            "(?<deadline>[^\"]+) /from (" + isoDatePattern + ")"
                    + " /to (" + isoDatePattern + ")");

    public static Command parse(String input) throws DukeException {
        final Matcher matcher = BASIC_COMMAND.matcher(input.trim());

        if(!matcher.matches()) {
            throw new InvalidCommandException("Invalid Command");
        }

        final String command = matcher.group("command");
        final String argument = matcher.group("arguments").trim();
        final boolean validIndex = argument.matches("-?\\d+");

        switch(command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (validIndex) {
                return new EditCommand("mark", Integer.parseInt(argument));
            } else {
                throw new InvalidIndexException();
            }

        case "unmark":
            if (validIndex) {
                return new EditCommand("unmark", Integer.parseInt(argument));
            } else {
                throw new InvalidIndexException();
            }


        case "delete":
            if (validIndex) {
                return new EditCommand("delete", Integer.parseInt(argument));
            } else {
                throw new InvalidIndexException();
            }

        case "todo":
            return new AddCommand("todo", new String[]{argument});

        case "deadline":
            Matcher deadlineFormat = DEADLINE_FORMAT.matcher(argument);

            if (deadlineFormat.matches()) {
                String desc = deadlineFormat.group(1);
                String byDate = parseDateTimeFormat(deadlineFormat.group(2));
                return new AddCommand("deadline", new String[]{desc, byDate});
            } else {
                throw new InvalidCommandException("Wrong deadline format");
            }

        case "event":
            Matcher eventFormat = EVENT_FORMAT.matcher(argument);

            if (eventFormat.matches()) {
                String desc = eventFormat.group(1);
                String fromDate = parseDateTimeFormat(eventFormat.group(2));
                String toDate = parseDateTimeFormat(eventFormat.group(3));
                return new AddCommand("event", new String[]{desc, fromDate, toDate});
            } else {
                throw new InvalidCommandException("Wrong event format");
            }

        default:
            throw new InvalidCommandException("Command not understood");
        }

    }

    /**
     * Return the string representing the dateTime input in MMM d yyyy h.mma format.
     *
     * @param input The dateTime string input from the user.
     * @return The string of the formatted DateTime input.
     */
    private static String parseDateTimeFormat(String input) {
        String[] dateTime = input.split(" ", 2);
        String dateTimeFormat = dateTime[0] + "T" + dateTime[1] + ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h.mma", Locale.ENGLISH);

        LocalDateTime deadline = LocalDateTime.parse(dateTimeFormat);
        return deadline.format(formatter);
    }
}
