package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class provides methods for parsing user input and converting date/time strings into
 * LocalDateTime or LocalDate objects. It also has methods for creating different types of tasks
 * based on user input.
 */

public class Parser {

    /**
     * Enum representing the types of commands that the parser can recognize.
     */
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, INVALID
    }

    /**
     * Parses a user command to determine its CommandType.
     *
     * @param command The user command to be parsed.
     * @return The CommandType representing the parsed command.
     */
    public CommandType parseCommandType(String command) {
        if (command.equals("bye")) {
            return CommandType.BYE;
        } else if (command.equals("list")) {
            return CommandType.LIST;
        } else if (command.startsWith("mark ")) {
            return CommandType.MARK;
        } else if (command.startsWith("unmark ")) {
            return CommandType.UNMARK;
        } else if (command.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (command.startsWith("event ")) {
            return CommandType.EVENT;
        } else if (command.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (command.startsWith("delete ")) {
            return CommandType.DELETE;
        } else {
            return CommandType.INVALID;
        }
    }

    /**
     * Parses a date/time string and converts it into a LocalDateTime object. Supports two date/time formats.
     *
     * @param dateTimeString The date/time string to be parsed.
     * @return The LocalDateTime object representing the parsed date/time.
     */
    public LocalDateTime parseCustomDateTime(String dateTimeString) {
        // Try parsing with "dd/MM/yyyy HHmm" format
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter1);
        } catch (Exception e) {
            // Parsing with the first format failed, try the second format below
        }

        // Try parsing with "yyyy-MM-dd" format
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateTimeString, formatter2);
            return date.atStartOfDay();
        } catch (Exception e) {
            // Both parsing attempts failed
            return null;
        }
    }

    /**
     * Parses a date string and converts it into a LocalDate object. Supports two date formats.
     *
     * @param dateString The date string to be parsed.
     * @return The LocalDate object representing the parsed date.
     */
    public LocalDate parseCustomDate(String dateString) {
        // Try parsing with "dd/MM/yyyy" format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (Exception e) {
            // Parsing with the first format failed, try the second format below
        }

        // Try parsing with "yyyy-MM-dd" format
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateString, formatter2);
        } catch (Exception e) {
            // Both parsing attempts failed
            return null;
        }
    }

    /**
     * Creates a Deadline task based on a description and a date/time string.
     *
     * @param description    The description of the Deadline task.
     * @param dateTimeString The date/time string for the task's deadline.
     * @return The created Deadline task or null if parsing fails.
     */
    public Deadline addDeadlineTask(String description, String dateTimeString) {
        LocalDateTime dateTime = parseCustomDateTime(dateTimeString);
        LocalDate date = parseCustomDate(dateTimeString);

        if (dateTime != null || date != null) {
            if (dateTime != null) {
                return new Deadline(description, dateTime);
            } else {
                return new Deadline(description, date);
            }
        }
        return null;
    }

    /**
     * Creates an Event task based on a description and two date/time strings for start and end times.
     *
     * @param description The description of the Event task.
     * @param from        The date/time string for the event's start time.
     * @param to          The date/time string for the event's end time.
     * @return The created Event task or null if parsing fails.
     */
    public Event addEventTask(String description, String from, String to) {
        LocalDateTime fromDateTime = parseCustomDateTime(from);
        LocalDate fromDate = parseCustomDate(from);
        LocalDateTime toDateTime = parseCustomDateTime(to);
        LocalDate toDate = parseCustomDate(to);

        if ((fromDateTime != null && toDateTime != null) || (fromDate != null && toDate != null)) {
            Event event;
            if (fromDateTime != null && toDateTime != null) {
                event = new Event(description, fromDateTime, toDateTime);
            } else {
                event = new Event(description, fromDate, toDate);
            }
            return event;
        }
        return null;
    }

    /**
     * Creates a ToDo task based on a description.
     *
     * @param description The description of the ToDo task.
     * @return The created ToDo task.
     */
    public ToDo addTodoTask(String description) {
        return new ToDo(description);
    }


}
