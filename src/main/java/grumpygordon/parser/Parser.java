package grumpygordon.parser;

import grumpygordon.tasks.*;
import grumpygordon.exceptions.*;
import grumpygordon.commands.*;
import grumpygordon.ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Parser {
    private static final String SAVED_FORMAT_SEPARATOR_REGEX = " \\| ";
    private static final String SPACE_REGEX = " ";
    private static final String MARK_REGEX = "mark\\s([0-9]+)$";
    private static final String UNMARK_REGEX = "unmark\\s([0-9]+)$";
    private static final String DELETE_REGEX = "delete\\s([0-9]+)$";
    private static final String DEADLINE_INFO_REGEX = ".*\\s+/by\\s+.*";
    private static final String EVENT_INFO_REGEX = ".*\\s+/from\\s+.*\\s+/to\\s+.*";
    private static final String DATETIME_REGEX = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]) \\d{2}:\\d{2}";
    public static boolean isValidDateTime(String datetime) {
        datetime = datetime.strip();
        return Pattern.matches(DATETIME_REGEX, datetime);
    }
    public static LocalDateTime parseDateTime(String datetime) throws GrumpyGordonDateTimeFormatException {
        try {
            if (isValidDateTime(datetime)) {
                datetime = datetime.strip();
                String[] arr = datetime.split(" ");
                String[] date = arr[0].split("-");
                String[] time = arr[1].split(":");
                return LocalDateTime.of(
                        Integer.parseInt(date[0]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[2]),
                        Integer.parseInt(time[0]),
                        Integer.parseInt(time[1]));
            }
        } catch (DateTimeException e) {
            throw new GrumpyGordonDateTimeFormatException("Invalid datetime.\n");
        }
        throw new GrumpyGordonDateTimeFormatException("Invalid datetime.\n");
    }
    public static Task parseStringToTask(String line) throws GrumpyGordonException {
        String[] parts = line.split(SAVED_FORMAT_SEPARATOR_REGEX);

        if (parts.length < 3) {
            throw new GrumpyGordonInitialisationException("Saved data is corrupted.\n");
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
                case "T":
                    // Parse the saved format for grumpygordon.tasks.Deadline
                    // Example: "T | 0 | Sleep"
                    return new Todo(description, isDone);
                case "D":
                    // Parse the saved format for grumpygordon.tasks.Deadline
                    // Example: "D | 1 | Buy groceries | 2023-08-31 12:00"
                    String deadlineBy = parts[3];
                    return new Deadline(description, parseDateTime(deadlineBy), isDone);
                case "E":
                    // Parse the saved format for grumpygordon.tasks.Event
                    // Example: "E | 0 | Team meeting | 2023-09-01 12:00 | 2023-09-02 14:00"
                    String eventFrom = parts[3];
                    String eventTo = parts[4];
                    return new Event(description, parseDateTime(eventFrom), parseDateTime(eventTo), isDone);
                default:
                    throw new GrumpyGordonInitialisationException("Saved data cannot be parsed.\n");
            }
        } catch (GrumpyGordonInitialisationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String[] parseDeadlineInfo(String deadlineInfo) {
        String desc = deadlineInfo.split(" /by ")[0];
        String by = deadlineInfo.split(" /by ")[1];
        return new String[] {desc, by};
    }

    public static String[] parseEventInfo(String eventInfo) {
        String desc = eventInfo.split(" /from ")[0];
        String from = eventInfo.split(" /from ")[1].split(" /to ")[0];
        String to = eventInfo.split(" /from ")[1].split(" /to ")[1];
        return new String[] {desc, from, to};
    }
    public static Command parseCommand(String userInput, TaskList tasks) throws GrumpyGordonException{
        userInput = userInput.strip();
        String[] parts = userInput.split(SPACE_REGEX, 2);

        if (parts.length == 0) {
            throw new GrumpyGordonInvalidCommandException("Command cannot be empty.\n");
        }

        String command = parts[0].toLowerCase();
        int taskIndex;
        String[] args;

        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                if (!Pattern.matches(MARK_REGEX, userInput)) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for mark is incorrect.\n");
                }

                try {
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                return new MarkCommand(taskIndex);
            case "unmark":
                if (!Pattern.matches(UNMARK_REGEX, userInput)) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for unmark is incorrect.\n");
                }

                try {
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                return new UnmarkCommand(taskIndex);
            case "delete":
                if (!Pattern.matches(DELETE_REGEX, userInput)) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for delete is incorrect.\n");
                }

                try {
                    taskIndex = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                if (taskIndex < 0 || taskIndex > tasks.size() - 1) {
                    throw new GrumpyGordonInvalidCommandException("Task number is invalid.\n");
                }

                return new DeleteCommand(taskIndex);
            case "todo":
                String todoInfo = parts[1];

                if (todoInfo.strip().isEmpty()) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for todo is incorrect.\n");
                }

                return new TodoCommand(todoInfo);
            case "deadline":
                String deadlineInfo = parts[1];
                if (!Pattern.matches(DEADLINE_INFO_REGEX, deadlineInfo)) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for deadline is incorrect.\n");
                }
                args = Parser.parseDeadlineInfo(deadlineInfo);
                return new DeadlineCommand(args[0], parseDateTime(args[1]));
            case "event":
                String eventInfo = parts[1];
                if (!Pattern.matches(EVENT_INFO_REGEX, eventInfo)) {
                    throw new GrumpyGordonInvalidCommandException("Command syntax for event is incorrect.\n");
                }
                args = Parser.parseEventInfo(eventInfo);
                return new EventCommand(args[0], parseDateTime(args[1]), parseDateTime(args[2]));
            default:
                break;
        }
        throw new GrumpyGordonInvalidCommandException("Invalid command.\n");
    }
}
