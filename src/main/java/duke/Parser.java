package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if (input.equalsIgnoreCase("bye")) return CommandType.BYE;
        if (input.equalsIgnoreCase("list")) return CommandType.LIST;
        if (input.startsWith("todo")) return CommandType.TODO;
        if (input.startsWith("deadline")) return CommandType.DEADLINE;
        if (input.startsWith("event")) return CommandType.EVENT;
        if (input.startsWith("mark")) return CommandType.MARK;
        if (input.startsWith("unmark")) return CommandType.UNMARK;
        if (input.startsWith("delete")) return CommandType.DELETE;
        if (input.startsWith("tasks on")) return CommandType.TASKS_ON_DATE;
        if (input.startsWith("find")) return CommandType.FIND;
        return CommandType.UNKNOWN;
    }

    /**
     * Parses a task represented as a string from the storage file.
     *
     * @param taskData The task data as a string.
     * @return A {@link Task} object based on the provided task data.
     * @throws DukeException If the task type is unknown or there's an error in parsing.
     */
    private static Task parseFileTask(String taskData) throws DukeException {
        String[] parts = taskData.split(" \\| ");
        switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (parts[1].equals("1")) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (parts[1].equals("1")) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (parts[1].equals("1")) event.markAsDone();
                return event;
            default:
                throw new DukeException("Unknown task type: " + parts[0]);
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
    public static boolean isWithinEventDate(Event event, LocalDate date) {
        LocalDate startDate = event.getFrom().toLocalDate();
        LocalDate endDate = event.getTo().toLocalDate();
        return (date.isEqual(startDate) || date.isEqual(endDate) || (date.isAfter(startDate) && date.isBefore(endDate)));
    }
}
