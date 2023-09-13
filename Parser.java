import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, INVALID
    }
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

    public ToDo addTodoTask(String description) {
        return new ToDo(description);
    }


}
