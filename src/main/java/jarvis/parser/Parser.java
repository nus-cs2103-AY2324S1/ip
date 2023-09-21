package jarvis.parser;

import jarvis.command.*;
import jarvis.exception.JarvisException;
import jarvis.exception.JarvisMissingValueException;
import jarvis.exception.JarvisUnrecognisedCommandException;
import jarvis.exception.JarvisWrongDateFormatException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {


    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Command parse(String fullCommand) throws JarvisException {
        String[] parts = fullCommand.split(" ", 2);
        String commandType = parts[0];

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodo(parts);
        case "deadline":
            return parseDeadline(parts);
        case "event":
            return parseEvent(parts);
        case "delete":
            return parseDelete(parts);
        case "mark":
            return parseMark(parts);
        case "unmark":
            return parseUnmark(parts);
        default:
            throw new JarvisUnrecognisedCommandException();
        }
    }

    private void ensureValidParts(String[] parts, String value, String command) throws JarvisException {
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JarvisMissingValueException(value, command);
        }
    }

    private Command parseTodo(String[] parts) throws JarvisException {
        ensureValidParts(parts, "description", "todo");
        return new AddCommand(new Todo(parts[1]));
    }


    private Command parseDeadline(String[] parts) throws JarvisException {
        ensureValidParts(parts, "description", "deadline");
        String[] deadlineParts = parts[1].split(" /by ", 2);
        ensureValidParts(deadlineParts, "deadline time", "deadline");
        try {
            LocalDateTime by = LocalDateTime.parse(deadlineParts[1], inputDateTimeFormatter);
            return new AddCommand(new Deadline(deadlineParts[0], by));
        } catch (DateTimeParseException e) {
            throw new JarvisWrongDateFormatException();
        }
    }

    private Command parseEvent(String[] parts) throws JarvisException {
        ensureValidParts(parts, "description", "event");
        String[] eventParts = parts[1].split(" /from ", 2);
        ensureValidParts(eventParts, "start time", "event");
        String[] timeParts = eventParts[1].split(" /to ", 2);
        ensureValidParts(timeParts, "end time", "event");
        try {
            LocalDateTime from = LocalDateTime.parse(timeParts[0], inputDateTimeFormatter);
            LocalDateTime to = LocalDateTime.parse(timeParts[1], inputDateTimeFormatter);
            return new AddCommand(new Event(eventParts[0], from, to));
        } catch (DateTimeParseException e) {
            throw new JarvisWrongDateFormatException();
        }
    }

    private Command parseDelete(String[] parts) throws JarvisException {
        ensureValidParts(parts, "task index", "delete");
        try {
            int deleteIndex = Integer.parseInt(parts[1]);
            return new DeleteCommand(deleteIndex);
        } catch (NumberFormatException e) {
            throw new JarvisException("Please provide a valid task index.");
        }
    }

    private Command parseMark(String[] parts) throws JarvisException {
        ensureValidParts(parts, "task index", "mark");
        try {
            int markIndex = Integer.parseInt(parts[1]);
            return new MarkCommand(markIndex);
        } catch (NumberFormatException e) {
            throw new JarvisException("Please provide a valid task index.");
        }
    }

    private Command parseUnmark(String[] parts) throws JarvisException {
        ensureValidParts(parts, "task index", "unmark");
        try {
            int unmarkIndex = Integer.parseInt(parts[1]);
            return new UnmarkCommand(unmarkIndex);
        } catch (NumberFormatException e) {
            throw new JarvisException("Please provide a valid task index.");
        }
    }
}