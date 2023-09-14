package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.Event;

/**
 * Represents a utility class to parse and interpret user input and task data.
 * <p>
 * This class provides static methods for parsing user input into specific command types,
 * reading task data, and converting date strings into {@link LocalDate} objects.
 * </p>
 */
public class Parser {
    /**
     * Parses the user input and returns its corresponding command type.
     *
     * @param input The user input to be parsed.
     * @return The corresponding {@link CommandType} based on the user input.
     */
    public static CommandType parseCommand(String input) {
        String command = input.split(" ")[0].toLowerCase();

        switch (command) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "delete":
            return CommandType.DELETE;
        case "tasks":
            if (input.startsWith("tasks on")) {
                return CommandType.TASKS_ON_DATE;
            } else {
                return CommandType.UNKNOWN;
            }
        case "find":
            return CommandType.FIND;
        case "sort":
            return CommandType.SORT;
        default:
            return CommandType.UNKNOWN;
        }
    }


    /**
     * Parse user's input and return the argument for that input.
     *
     * @param input user input from System.in
     * @return argument fields of the input
     * @throws DukeException if the command requires argument(s) and it is missing
     */
    public static String parseArgs(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("You are missing arguments!");
        }
    }
    /**
     * Converts a date string from the user input into a {@link LocalDate} object.
     *
     * @param input The user input containing the date string.
     * @return The {@link LocalDate} representation of the provided date string.
     * @throws DukeException If the date format is invalid or there's an error in parsing.
     */
    public static LocalDate getLocalDate(String input) throws DukeException {
        String[] dateParts = input.split(" ");
        if (dateParts.length < 3) {
            throw new DukeException("Please provide a valid date in the format d/M/yyyy.");
        }
        LocalDate givenDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            givenDate = LocalDate.parse(dateParts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy.");
        }
        return givenDate;
    }

    /**
     * Determines if a specific date falls within the start and end dates of an event.
     *
     * @param event The event to check against.
     * @param date  The date to verify.
     * @return {@code true} if the date is within the event's date range, otherwise {@code false}.
     */
    public static boolean checkWithinEventDate(Event event, LocalDate date) {
        LocalDate startDate = event.getFrom().toLocalDate();
        LocalDate endDate = event.getTo().toLocalDate();
        boolean isStartDate = date.isEqual(startDate);
        boolean isEndDate = date.isEqual(endDate);
        boolean isAfterStartDate = date.isAfter(startDate);
        boolean isBeforeEndDate = date.isAfter(endDate);
        return isStartDate || isEndDate || (isAfterStartDate && isBeforeEndDate);
    }
}
