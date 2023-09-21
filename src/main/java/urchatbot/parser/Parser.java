package urchatbot.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import urchatbot.commands.ClearCommand;
import urchatbot.commands.Command;
import urchatbot.commands.DeadlineCommand;
import urchatbot.commands.DeleteCommand;
import urchatbot.commands.EventCommand;
import urchatbot.commands.ExitCommand;
import urchatbot.commands.FindCommand;
import urchatbot.commands.FindFreeTimeCommand;
import urchatbot.commands.ListCommand;
import urchatbot.commands.MarkCommand;
import urchatbot.commands.PrintCommand;
import urchatbot.commands.TodoCommand;
import urchatbot.commands.UnmarkCommand;
import urchatbot.exception.URChatBotException;
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class Parser {

    /**
     * Categorises and returns subclass of Command type based on entered command.
     *
     * @param command Command entered by users.
     * @return new command class instance
     * @throws URChatBotException If command undefined.
     */
    public static Command parse(String command) throws URChatBotException {
        String commandType = getCommandType(command);
        switch (commandType) {
        case "TODO":
            return parseTodoCommand(command);
        case "DEADLINE":
            return parseDeadlineCommand(command);
        case "EVENT":
            return parseEventCommand(command);
        case "LIST":
            return parseListCommand(command);
        case "MARK":
            return parseNumericCommand(command, "MARK");
        case "UNMARK":
            return parseNumericCommand(command, "UNMARK");
        case "CLEAR":
            return parseClearCommand(command);
        case "DELETE":
            return parseNumericCommand(command, "DELETE");
        case "PRINT":
            return parsePrintCommand(command);
        case "FIND":
            return parseFindCommand(command);
        case "FINDFREETIME":
            return parseFindFreeTimeCommand(command);
        case "BYE":
            return parseExitCommand(command);
        default:
            assert false : commandType;
            throw new URChatBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Categorises and returns subclass of Command type based on entered command.
     *
     * @param time Time in the format of "yyyy-MM-dd HH:mm" or "yyyy-MM-dd" or "HH:mm."
     * @return a String of time in the format of "MMM d yyyy HH:mm" or "MMM d yyyy" or "HH:mm"
     */
    private static String changeTimeFormat(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime d = LocalDateTime.parse(time, formatter);
            return d.format(DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate d = LocalDate.parse(time);
                return d.format(DateTimeFormatter.ofPattern("dd MM yyyy"));
            } catch (DateTimeParseException err) {
                try {
                    LocalTime t = LocalTime.parse(time);
                    return t.format(DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException tErr) {
                    return null;
                }
            }
        }
    }

    private static String getCommandType(String command) {
        return command.split("\\s")[0].toUpperCase();
    }

    private static Command parseTodoCommand(String command) throws URChatBotException {
        validateCommandNotEmpty(command, 4);
        String task = extractTask(command, "todo");
        return new TodoCommand(task);
    }
    private static Command parseDeadlineCommand(String command) throws URChatBotException {
        validateCommandNotEmpty(command, 8);
        validateKeywordsExistenceForDeadline(command);

        String task = extractTask(command, "deadline", "/by");
        String by = extractField(command, "/by");
        String time = validateAndChangeTimeFormat(by);

        return new DeadlineCommand(task, time);
    }

    private static Command parseEventCommand(String command) throws URChatBotException {
        validateCommandNotEmpty(command, 5);
        validateKeywordsExistenceForEvent(command);

        String task = extractTask(command, "event", "/from");
        String from = extractField(command, "/from", "/to");
        String to = extractField(command, "/to");

        validateFieldsNotEmpty(task, from, to);

        String timeFrom = validateAndChangeTimeFormat(from);
        String timeTo = validateAndChangeTimeFormat(to);

        LocalDateTime dateTimeFrom = parseDateTime(from);
        LocalDateTime dateTimeTo = parseDateTime(to);

        validateDateRange(dateTimeFrom, dateTimeTo);

        return new EventCommand(task, timeFrom, timeTo);
    }
  
    private static Command parseClearCommand(String command) {
        assert !command.isBlank(): "Command should not be blank!";
        return new ClearCommand(command);
    }
    private static Command parseListCommand(String command) {
        assert !command.isBlank(): "Command should not be blank!";
        return new ListCommand(command);
    }

    private static Command parseNumericCommand(String command, String commandName) throws URChatBotException {
        int value;
        try {
            value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
            assert value > -1: "Task number should be more than -1!";
        } catch (NumberFormatException e) {
            throw new URChatBotException("OOPS!!! Please provide a valid numeric value for the " + commandName + " command.");
        }

        if (value < 1) {
            throw new URChatBotException("OOPS!!! The numeric value for the " + commandName + " command must be greater than zero.");
        }

        switch (commandName) {
        case "MARK":
            return new MarkCommand(value - 1);
        case "UNMARK":
            return new UnmarkCommand(value - 1);
        case "DELETE":
            return new DeleteCommand(value - 1);
        default:
            throw new URChatBotException("OOPS!!! Invalid command: " + commandName);
        }
    }

    private static Command parsePrintCommand(String command) throws URChatBotException {
        assert !command.isBlank(): "Command should not be blank!";

        String date = extractTask(command, "print");

        String formattedDate = changeTimeFormat(date);
        if (formattedDate == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'.");
        }
        return new PrintCommand(formattedDate);
    }
    private static Command parseFindCommand(String command) throws URChatBotException {
        String searchWord = extractTask(command, "find");
        if (searchWord == null) {
            throw new URChatBotException("No words input for searching.");
        }
        return new FindCommand(searchWord);
    }
    private static Command parseExitCommand(String command) {
        return new ExitCommand(command);
    }
    private static Command parseFindFreeTimeCommand(String command) throws URChatBotException {
        validateCommandNotEmpty(command, 12);
        validateKeywordsExistenceForEvent(command);
        String timeDurationString = extractTask(command, "findFreeTime", "/from");
        int timeDuration =  Integer.parseInt(timeDurationString.replaceAll("[^0-9]", ""));
        String from = extractField(command, "/from", "/to");
        String to = extractField(command, "/to");

        validateFieldsNotEmpty(timeDurationString, from, to);

        validateTimeFormat(from);
        validateTimeFormat(to);

        LocalDateTime dateTimeFrom = parseDateTime(from);
        LocalDateTime dateTimeTo = parseDateTime(to);

        validateDateRange(dateTimeFrom, dateTimeTo);

        return new FindFreeTimeCommand(timeDuration, dateTimeFrom.toLocalDate(), dateTimeTo.toLocalDate());
    }
    private static void validateCommandNotEmpty(String command, int minLength) throws URChatBotException {
        if (command.trim().length() <= minLength) {
            throw new URChatBotException("OOPS!!! The description cannot be empty.");
        }
    }

    private static void validateKeywordsExistenceForEvent(String command) throws URChatBotException {
        if (!command.contains("/from") || !command.contains("/to")) {
            throw new URChatBotException("OOPS!!! The /from or /to keywords are missing in the command.");
        }
    }
    private static void validateKeywordsExistenceForDeadline(String command) throws URChatBotException {
        if (!command.contains("/by")) {
            throw new URChatBotException("OOPS!!! The /by keywords are missing in the command.");
        }
    }
    private static void validateFieldsNotEmpty(String task, String from, String to) throws URChatBotException {
        if (task.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new URChatBotException("OOPS!!! The task name, from, and to fields cannot be empty.");
        }
    }

    private static LocalDateTime parseDateTime(String time) {
        try {
            // Attempt to parse with both date-time and date-only formats
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            DateTimeFormatter formatterDateOnly = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                LocalDateTime dateTimeWithTime = LocalDateTime.parse(time, formatterWithTime);
                return dateTimeWithTime;
            } catch (DateTimeParseException e) {
                try {
                    // Try parsing without the date part for the "HH:mm" format
                    LocalTime timeOnly = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                    // Get the current date and combine it with the parsed time
                    LocalDate currentDate = LocalDate.now();
                    // Check if the timeOnly is earlier than the current time; if yes, assume it's for the next day
                    if (timeOnly.isBefore(LocalTime.now())) {
                        currentDate = currentDate.plusDays(1);
                    }
                    return LocalDateTime.of(currentDate, timeOnly);
                } catch (DateTimeParseException ex) {
                    LocalDate dateOnly = LocalDate.parse(time, formatterDateOnly);
                    // Set the time part to midnight (00:00) for date-only input
                    return dateOnly.atStartOfDay();
                }
            }
        } catch (DateTimeParseException e) {
            return null; // Invalid format
        }
    }

    private static String extractTask(String command, String keyword) {
        return command.substring(command.indexOf(keyword) + keyword.length()).trim();
    }
    private static String extractTask(String command, String commandType, String keyword) {
        int startIndex = command.indexOf(commandType) + keyword.length();
        int endIndex = command.indexOf(keyword);
        return command.substring(startIndex, endIndex).trim();
    }

    private static String extractField(String command, String fieldName) {
        return command.substring(command.indexOf(fieldName) + fieldName.length()).trim();
    }

    private static String extractField(String command, String fieldName1, String fieldName2) {
        int startIndex = command.indexOf(fieldName1) + fieldName1.length();
        int endIndex = command.indexOf(fieldName2);
        return command.substring(startIndex, endIndex).trim();
    }

    private static String validateAndChangeTimeFormat(String time) throws URChatBotException {
        String formattedTime = changeTimeFormat(time);
        if (formattedTime == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'.");
        }
        return formattedTime;
    }

    private static void validateTimeFormat(String time) throws URChatBotException {
        String formattedTime = changeTimeFormat(time);
        if (formattedTime == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'.");
        }
    }

    private static void validateDateRange(LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) throws URChatBotException {
        if (dateTimeFrom.isAfter(dateTimeTo)) {
            throw new URChatBotException("OOPS!!! The /from date must be before the /to date.");
        }
    }

}
