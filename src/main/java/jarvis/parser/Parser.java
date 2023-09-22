package jarvis.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.command.AddCommand;
import jarvis.command.Command;
import jarvis.command.DeleteCommand;
import jarvis.command.ExitCommand;
import jarvis.command.FindCommand;
import jarvis.command.ListCommand;
import jarvis.command.MarkCommand;
import jarvis.command.UnmarkCommand;
import jarvis.exception.JarvisException;
import jarvis.exception.JarvisMissingValueException;
import jarvis.exception.JarvisUnrecognisedCommandException;
import jarvis.exception.JarvisWrongDateFormatException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Todo;

/**
 * The Parser class is responsible for interpreting user inputs
 * and converting them into executable Command objects for the Jarvis application.
 *
 * <p>It recognizes specific keywords in the user input (such as "todo", "deadline",
 * "event", etc.) and creates the corresponding Command objects with the necessary
 * information extracted from the input.</p>
 */
public class Parser {


    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Parses user input into a recognizable Command object.
     *
     * @param fullCommand The entire user input string.
     * @return The Command object corresponding to the user input.
     * @throws JarvisException If the user input is not recognized or has an incorrect format.
     */
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
        case "find":
            return parseFind(parts);
        default:
            throw new JarvisUnrecognisedCommandException();
        }
    }

    private void ensureValidParts(String[] parts, String value, String command) throws JarvisException {
        assert parts != null : "Parts array should not be null";

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
            LocalDateTime by = LocalDateTime.parse(deadlineParts[1], INPUT_DATE_TIME_FORMATTER);
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
            LocalDateTime from = LocalDateTime.parse(timeParts[0], INPUT_DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(timeParts[1], INPUT_DATE_TIME_FORMATTER);
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

    private Command parseFind(String[] parts) throws JarvisException {
        ensureValidParts(parts, "keyword", "find");
        return new FindCommand(parts[1]);
    }
}
