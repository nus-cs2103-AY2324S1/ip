import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toUpperCase();
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case TODO:
            return new AddTodoCommand(parseArgument(parts));
        case DEADLINE:
            return parseAddDeadlineCommand(parseArgument(parts));
        case EVENT:
            return parseAddEventCommand(parseArgument(parts));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(parseArgument(parts)) - 1);
        case CHECK:
            return new CheckCommand(Parser.parseArgument(parts));
        case TODAY:
            return new TodayCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    public static String parseArgument(String[] parts) {
        if (parts.length > 1) {
            return parts[1];
        }
        return "";
    }

    private static AddDeadlineCommand parseAddDeadlineCommand(String argument) throws InvalidFormatException {
        try {
            String[] deadlineParts = argument.split("/by");

            if (deadlineParts.length < 2) {
                throw new InvalidFormatException("Please use the format: deadline <description> /by <d/M/yyyy HHmm>.");
            }

            String description = deadlineParts[0].trim();
            String by = deadlineParts[1].trim();

            LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddDeadlineCommand(description, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date and time format. Please use the format: d/M/yyyy HHmm.");
        }
    }

    private static AddEventCommand parseAddEventCommand(String argument) throws InvalidFormatException {
        try {
            String[] eventParts = argument.split("/at");

            if (eventParts.length < 2) {
                throw new InvalidFormatException("Please use the format: event <description> /at <d/M/yyyy HHmm>");
            }

            String description = eventParts[0].trim();
            String at = eventParts[1].trim();

            LocalDateTime dateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return new AddEventCommand(description, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Invalid date and time format. Please use the format: d/M/yyyy HHmm.");
        }

    }
}
