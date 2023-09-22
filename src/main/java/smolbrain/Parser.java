package smolbrain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import smolbrain.command.AddCommand;
import smolbrain.command.Command;
import smolbrain.command.DeleteCommand;
import smolbrain.command.ExitCommand;
import smolbrain.command.FindCommand;
import smolbrain.command.InvalidCommand;
import smolbrain.command.ListCommand;
import smolbrain.command.MarkCommand;
import smolbrain.command.PriorityCommand;
import smolbrain.command.UnmarkCommand;
import smolbrain.exception.InvalidDateTimeException;
import smolbrain.exception.InvalidNumberException;
import smolbrain.exception.InvalidPriorityException;
import smolbrain.exception.InvalidRangeException;
import smolbrain.exception.MissingDescriptionException;
import smolbrain.exception.MissingKeywordException;
import smolbrain.exception.MissingTimeException;
import smolbrain.task.Deadline;
import smolbrain.task.Event;
import smolbrain.task.Task;
import smolbrain.task.Todo;

/**
 * Parses any commands or string input.
 */
public class Parser {

    private static String descr;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HHmm");
    private static LocalDateTime dateTime = LocalDateTime.now();
    private static LocalDateTime dateTime2 = LocalDateTime.now();

    /**
     * Creates a parser.
     */
    public Parser() {
    }

    /**
     * Parses a list command.
     *
     * @param words Array of strings that was split by spaces.
     * @return List command.
     */
    public static Command parseList(String[] words) {
        return new ListCommand();
    }

    /**
     * Parses a todo command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Todo task.
     * @throws MissingDescriptionException If there was no description provided.
     */
    public static Task parseTodo(String[] words) throws MissingDescriptionException {
        descr = "";
        if (words.length < 2) {
            throw new MissingDescriptionException("todo");
        }
        for (int i = 1; i < words.length; i++) {
            descr = descr + words[i] + " ";
        }
        descr = descr.substring(0, descr.length() - 1);
        return new Todo(descr);
    }

    /**
     * Parses a deadline command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Deadline task.
     * @throws MissingDescriptionException If there was no description provided.
     * @throws MissingTimeException If there was no time provided.
     * @throws InvalidDateTimeException If the provided date or time was invalid.
     */
    public static Task parseDeadline(String[] words) throws MissingDescriptionException, MissingTimeException,
            InvalidDateTimeException {
        boolean isBy = false;
        descr = "";
        String byText = "";
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                isBy = true;
                continue;
            }
            if (isBy) {
                byText = byText + words[i] + " ";
            } else {
                descr = descr + words[i] + " ";
            }
        }
        if (descr.equals("")) {
            throw new MissingDescriptionException("deadline");
        } else if (byText.equals("")) {
            throw new MissingTimeException("deadline", "ending");
        }
        descr = descr.substring(0, descr.length() - 1);
        byText = byText.substring(0, byText.length() - 1);
        try {
            dateTime = LocalDateTime.parse(byText, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
        return new Deadline(descr, dateTime);
    }

    /**
     * Parses an event command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Event task.
     * @throws MissingDescriptionException If there was no description provided.
     * @throws MissingTimeException If there was no time provided.
     * @throws InvalidDateTimeException If the provided date or time was invalid.
     */
    public static Task parseEvent(String[] words) throws MissingDescriptionException, MissingTimeException,
            InvalidDateTimeException {
        boolean isFrom = false;
        boolean isTo = false;
        descr = "";
        String fromText = "";
        String toText = "";
        String[] val = parseEventText(words, isFrom, isTo, fromText, toText);
        descr = descr.substring(0, descr.length() - 1);
        fromText = val[0].substring(0, val[0].length() - 1);
        toText = val[1].substring(0, val[1].length() - 1);
        try {
            dateTime = LocalDateTime.parse(fromText, formatter);
            dateTime2 = LocalDateTime.parse(toText, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
        return new Event(descr, dateTime, dateTime2);
    }

    private static String[] parseEventText(String[] words, boolean isFrom, boolean isTo, String fromText,
                String toText) throws MissingDescriptionException, MissingTimeException {
        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                isFrom = true;
                continue;
            } else if (words[i].equals("/to")) {
                isTo = true;
                continue;
            }
            if (isTo) {
                toText = toText + words[i] + " ";
            } else if (isFrom) {
                fromText = fromText + words[i] + " ";
            } else {
                descr = descr + words[i] + " ";
            }
        }
        if (descr.equals("")) {
            throw new MissingDescriptionException("event");
        } else if (fromText.equals("")) {
            throw new MissingTimeException("event", "start");
        } else if (toText.equals("")) {
            throw new MissingTimeException("event", "end");
        }
        String[] val = {fromText, toText};
        return val;
    }

    /**
     * Parses a mark command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Mark command.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidRangeException If the provided number is out of range.
     */
    public static Command parseMark(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("mark");
        }
        try {
            return new MarkCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("mark");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    /**
     * Parses a unmark command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Unmark command.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidRangeException If the provided number is out of range.
     */
    public static Command parseUnmark(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("mark");
        }
        try {
            return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("mark");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    /**
     * Parses a delete command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Delete command.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidRangeException If the provided number is out of range.
     */
    public static Command parseDelete(String[] words) throws InvalidNumberException, InvalidRangeException {
        if (words.length < 2) {
            throw new InvalidNumberException("delete");
        }
        try {
            return new DeleteCommand(Integer.parseInt(words[1]) - 1);
        } catch (NumberFormatException ex) {
            throw new InvalidNumberException("delete");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidRangeException();
        }
    }

    /**
     * Parses a find command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Find command.
     * @throws MissingKeywordException If keyword was not given by user.
     */
    public static Command parseFind(String[] words) throws MissingKeywordException {
        descr = "";
        if (words.length < 2) {
            throw new MissingKeywordException();
        }
        for (int i = 1; i < words.length; i++) {
            descr = descr + words[i] + " ";
        }
        descr = descr.substring(0, descr.length() - 1);
        return new FindCommand(descr);
    }

    /**
     * Parses a priority command.
     *
     * @param words Array of strings that was split by spaces.
     * @return Priority command.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidPriorityException If the provided priority level is out of range.
     */
    public static Command parsePriority(String[] words) throws InvalidNumberException, InvalidPriorityException {
        if (words.length < 2) {
            throw new InvalidNumberException("select the task");
        } else if (words.length < 3) {
            throw new InvalidNumberException("set as priority level");
        }
        int id;
        int level;
        try {
            id = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("select the task");
        }
        try {
            level = Integer.parseInt(words[2]);
            if (!(level >= 0 && level <= 3)) {
                throw new InvalidPriorityException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("set as priority level");
        }
        return new PriorityCommand(id, level);
    }

    /**
     * Parses the given input by the user including passing it into its separate parse functions.
     *
     * @param input String of command.
     * @return Command that was parsed.
     * @throws MissingDescriptionException If there was no description provided.
     * @throws MissingTimeException If there was no time provided.
     * @throws InvalidDateTimeException If the provided date or time was invalid.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidPriorityException If the provided priority level is invalid.
     * @throws InvalidRangeException If the provided number is out of range.
     * @throws MissingKeywordException If no keyword was provided.
     */
    public static Command parse(String input) throws MissingDescriptionException, MissingTimeException,
            InvalidDateTimeException, InvalidNumberException, InvalidRangeException, MissingKeywordException,
            InvalidPriorityException {

        String[] words = input.split(" ");

        switch (words[0]) {
        case "list":
            return parseList(words);

        case "todo":
            return new AddCommand(parseTodo(words));

        case "deadline":
            return new AddCommand(parseDeadline(words));

        case "event":
            return new AddCommand(parseEvent(words));

        case "mark":
            return parseMark(words);

        case "unmark":
            return parseUnmark(words);

        case "delete":
            return parseDelete(words);

        case "bye":
            return new ExitCommand();

        case "find":
            return parseFind(words);

        case "priority":
            return parsePriority(words);

        default:
            return new InvalidCommand();
        }
    }

    /**
     * Used for save file parsing, it parses the given input by the user including
     * passing it into its separate parse functions.
     *
     * @param input String of command.
     * @return Task parsed.
     * @throws MissingDescriptionException If there was no description provided.
     * @throws MissingTimeException If there was no time provided.
     * @throws InvalidDateTimeException If the provided date or time was invalid.
     * @throws InvalidNumberException If the provided number cannot be parsed.
     * @throws InvalidRangeException If the provided number is out of range.
     */
    public static Task parseLoading(String input) throws MissingDescriptionException, MissingTimeException,
            InvalidDateTimeException, InvalidNumberException, InvalidRangeException {

        String[] words = input.split(" ");

        switch (words[0]) {

        case "todo":
            return parseTodo(words);

        case "deadline":
            return parseDeadline(words);

        case "event":
            return parseEvent(words);

        default:
            return new Task("");

        }
    }


}
