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
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String FIND_COMMAND = "find";

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
        case EXIT_COMMAND:
            return new ExitCommand();
        case LIST_COMMAND:
            return new ListCommand();
        case TODO_COMMAND:
            return parseTodo(parts);
        case DEADLINE_COMMAND:
            return parseDeadline(parts);
        case EVENT_COMMAND:
            return parseEvent(parts);
        case DELETE_COMMAND:
            return parseDelete(parts);
        case MARK_COMMAND:
            return parseMark(parts);
        case UNMARK_COMMAND:
            return parseUnmark(parts);
        case FIND_COMMAND:
            return parseFind(parts);
        default:
            throw new JarvisUnrecognisedCommandException();
        }
    }

    private String[] ensureValidParts(String[] parts, String[] parameters, String[] delimiters) throws JarvisException {
        assert parts != null : "Parts array should not be null";

        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JarvisMissingValueException(parameters[0], parts[0]);
        }

        // Start by using the original parts[1] for extraction
        String remainingPart = parts[1];
        String[] extractedParameters = new String[parameters.length];

        // Iterate through each delimiter and extract the corresponding parameter
        for (int i = 0; i < delimiters.length; i++) {
            if (remainingPart.contains(delimiters[i])) {
                String[] splitParts = remainingPart.split(delimiters[i], 2);
                extractedParameters[i] = splitParts[0].trim();
                remainingPart = splitParts[1];
            } else {
                throw new JarvisMissingValueException(parameters[i + 1], parts[0]);
            }
        }

        // The last parameter will be the remaining content after all splits
        extractedParameters[parameters.length - 1] = remainingPart.trim();

        return extractedParameters;
    }

    private LocalDateTime parseDateTime(String dateTime) throws JarvisWrongDateFormatException {
        try {
            return LocalDateTime.parse(dateTime, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JarvisWrongDateFormatException();
        }
    }

    private Command parseTodo(String[] parts) throws JarvisException {
        String[] taskParameters = new String[]{"description"};
        String[] delimiters = new String[]{}; // no delimiters
        String[] parameterValues = ensureValidParts(parts, taskParameters, delimiters);
        String description = parameterValues[0];
        return new AddCommand(new Todo(description));
    }

    private Command parseDeadline(String[] parts) throws JarvisException {
        String[] taskParameters = new String[]{"description", "deadline time"};
        String[] delimiters = new String[]{" /by "};
        String[] parameterValues = ensureValidParts(parts, taskParameters, delimiters);
        String description = parameterValues[0];
        LocalDateTime by = parseDateTime(parameterValues[1]);
        return new AddCommand(new Deadline(description, by));
    }

    private Command parseEvent(String[] parts) throws JarvisException {
        String[] taskParameters = new String[]{"description", "start time", "end time"};
        String[] delimiters = new String[]{" /from ", " /to "};
        String[] parameterValues = ensureValidParts(parts, taskParameters, delimiters);
        String description = parameterValues[0];
        LocalDateTime from = parseDateTime(parameterValues[1]);
        LocalDateTime to = parseDateTime(parameterValues[2]);
        return new AddCommand(new Event(description, from, to));
    }

    private Command parseFind(String[] parts) throws JarvisException {
        String[] taskParameters = new String[]{"keyword"};
        String[] delimiters = new String[]{}; // no delimiters
        String[] parameterValues = ensureValidParts(parts, taskParameters, delimiters);
        String keyword = parameterValues[0];
        return new FindCommand(keyword);
    }

    private int parseIndex(String[] parts) throws JarvisException {
        String[] taskParameters = new String[]{"task index"};
        String[] delimiters = new String[]{}; // no delimiters
        String[] parameterValues = ensureValidParts(parts, taskParameters, delimiters);
        String indexString = parameterValues[0];
        try {
            return Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            throw new JarvisException("Please provide a valid task index.");
        }
    }

    private Command parseDelete(String[] parts) throws JarvisException {
        int deleteIndex = parseIndex(parts);
        return new DeleteCommand(deleteIndex);
    }

    private Command parseMark(String[] parts) throws JarvisException {
        int markIndex = parseIndex(parts);
        return new MarkCommand(markIndex);
    }

    private Command parseUnmark(String[] parts) throws JarvisException {
        int unmarkIndex = parseIndex(parts);
        return new UnmarkCommand(unmarkIndex);
    }
}
