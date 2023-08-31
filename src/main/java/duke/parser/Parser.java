package duke.parser;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.commands.*;
import duke.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    public static Command parse(String command, boolean isRestoring) throws DukeException {
        if (command.trim().equals("")) return null;

        String[] parsedText = parseText(command);
        String action = parsedText[0];
        String arguments = parsedText[1];
        boolean marked = false;
        if (isRestoring) {
            String marker = arguments.substring(arguments.length() - 1);
            marked = marker.equals("1");
            arguments = arguments.substring(0, arguments.length() - 1);
        }

        switch (action) {

        case AddCommand.COMMAND_WORD_D:
        case AddCommand.COMMAND_WORD_E:
        case AddCommand.COMMAND_WORD_T:
            return handleAdd(action, arguments, marked);
        case DeleteCommand.COMMAND_WORD:
            if (arguments.equals("")) throw new InvalidArgumentException();
            return new DeleteCommand(parseArgs(arguments));
        case ListCommand.COMMAND_WORD:
            if (!arguments.equals("")) {
                throw new InvalidCommandException();
            }
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case MarkCommand.COMMAND_WORD_UNMARK:
            if (arguments.equals("")) throw new InvalidArgumentException();
            return new MarkCommand(false, parseArgs(arguments));
        case MarkCommand.COMMAND_WORD_MARK:
            if (arguments.equals("")) throw new InvalidArgumentException();
            return new MarkCommand(true, parseArgs(arguments));
        default:
            throw new InvalidCommandException();
        }
    }

    protected static AddCommand handleAdd(String action, String args, boolean marked) throws DukeException {
        if (args.equals("")) throw new InvalidArgumentException();
        Task task;
        switch (action) {
        case AddCommand.COMMAND_WORD_D:
            task = parseDeadline(args, marked);
            break;
        case AddCommand.COMMAND_WORD_E:
            task = parseEvent(args, marked);
            break;
        case AddCommand.COMMAND_WORD_T:
            task = new Todo(args, marked);
            break;
        default:
            throw new InvalidCommandException();
        }

        return new AddCommand(task);
    }

    private static Deadline parseDeadline(String text, boolean marked) throws InvalidDeadlineException {
        String[] deadline = text.split(" /by ");
        if (deadline.length != 2) {
            throw new InvalidDeadlineException();
        }

        LocalDateTime parsedDateTime = parseDateTime(deadline[1]);
        if (parsedDateTime == null) {
            throw new InvalidDeadlineException();
        }

        return new Deadline(deadline[0], parsedDateTime, marked);
    }

    private static Event parseEvent(String text, boolean marked) throws InvalidEventException {
        String[] first = text.split(" /from ");
        if (first.length != 2) {
            throw new InvalidEventException();
        }
        String[] second = first[1].split(" /to ");
        if (second.length != 2) {
            throw new InvalidEventException();
        }
        LocalDateTime fromDate = parseDateTime(second[0]);
        LocalDateTime toDate = parseDateTime(second[1]);

        if (fromDate == null || toDate == null) {
            throw new InvalidEventException();
        }

        return new Event(first[0], fromDate, toDate, marked);
    }

    protected static String[] parseText(String text) {
        String[] words = text.trim().split(" ");
        String[] remaining = Arrays.copyOfRange(words, 1, words.length);
        String restOfText = String.join(" ", remaining);

        return new String[] {words[0], restOfText};
    }

    protected static int parseArgs(String args) throws DukeException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException();
        }
    }

    protected static LocalDateTime parseDateTime(String text) {
        String[] datetime = text.split(" ");
        LocalDateTime parsedDateTime;
        try {
            if (datetime.length == 2) {
                String dateTimeString = datetime[0] + "T" + datetime[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm");
                parsedDateTime = LocalDateTime.parse(dateTimeString, formatter);
            } else {
                return null;
            }
        } catch (DateTimeParseException ex) {
            return null;
        }

        return parsedDateTime;
    }
}
