package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into a Command object for execution.
     *
     * @param input the text input by the user
     * @param isRestoring boolean value if this is parsing from a data file or real user input
     * @return Command to be executed
     * @throws DukeException when the user passes in invalid input
     */
    public static Command parse(String input, boolean isRestoring) throws DukeException {
        if (input.trim().equals("")) {
            return null;
        }

        String[] parsedText = parseText(input);
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
            if (arguments.equals("")) {
                throw new InvalidArgumentException();
            }
            return new DeleteCommand(parseArgs(arguments));
        case ListCommand.COMMAND_WORD:
            if (!arguments.equals("")) {
                throw new InvalidCommandException();
            }
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case MarkCommand.COMMAND_WORD_UNMARK:
            if (arguments.equals("")) {
                throw new InvalidArgumentException();
            }
            return new MarkCommand(false, parseArgs(arguments));
        case MarkCommand.COMMAND_WORD_MARK:
            if (arguments.equals("")) {
                throw new InvalidArgumentException();
            }
            return new MarkCommand(true, parseArgs(arguments));
        case FindCommand.COMMAND_WORD:
            if (arguments.equals("")) {
                throw new InvalidArgumentException();
            }
            return new FindCommand(arguments);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses user input for adding into tasks.
     *
     * @param taskInput the type of Task
     * @param args the arguments to be parsed for the Task
     * @param isMarked boolean value if the Task is marked
     * @return AddCommand to be executed
     * @throws DukeException if there is invalid user input
     */
    protected static AddCommand handleAdd(String taskInput, String args, boolean isMarked) throws DukeException {
        if (args.equals("")) {
            throw new InvalidArgumentException();
        }
        Task task;
        switch (taskInput) {
        case AddCommand.COMMAND_WORD_D:
            task = parseDeadline(args, isMarked);
            break;
        case AddCommand.COMMAND_WORD_E:
            task = parseEvent(args, isMarked);
            break;
        case AddCommand.COMMAND_WORD_T:
            task = new Todo(args, isMarked);
            break;
        default:
            throw new InvalidCommandException();
        }

        return new AddCommand(task);
    }

    /**
     * Parses user text into the action type and the arguments
     *
     * @param text the text to be parsed
     * @return String[] where the first index is the action type and second index is the arguments
     */
    protected static String[] parseText(String text) {
        String[] words = text.trim().split(" ");
        String[] remaining = Arrays.copyOfRange(words, 1, words.length);
        String restOfText = String.join(" ", remaining);

        return new String[] {words[0], restOfText};
    }

    /**
     * Parse string arguments into integers.
     *
     * @param args the string number to be parsed
     * @return the integer
     * @throws DukeException if an invalid string of number is provided
     */
    protected static int parseArgs(String args) throws DukeException {
        try {
            return Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses a string into a LocalDateTime object.
     *
     * @param text the string to be parsed
     * @return LocalDateTime object after parsed
     */
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

    /**
     * Parses arguments for the deadline action type.
     *
     * @param text the arguments to be parsed
     * @param isMarked boolean value if the Task is marked
     * @return Deadline object created by the arguments
     * @throws InvalidDeadlineException if invalid arguments are provided
     */
    private static Deadline parseDeadline(String text, boolean isMarked) throws InvalidDeadlineException {
        String[] deadline = text.split(" /by ");
        if (deadline.length != 2) {
            throw new InvalidDeadlineException();
        }

        LocalDateTime parsedDateTime = parseDateTime(deadline[1]);
        if (parsedDateTime == null) {
            throw new InvalidDeadlineException();
        }

        return new Deadline(deadline[0], parsedDateTime, isMarked);
    }

    /**
     * Parses arguments for the event action type.
     *
     * @param text the arguments to be parsed
     * @param isMarked boolean value if the Task is marked
     * @return Event object created by the arguments
     * @throws InvalidEventException if invalid arguments are provided
     */
    private static Event parseEvent(String text, boolean isMarked) throws InvalidEventException {
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

        return new Event(first[0], fromDate, toDate, isMarked);
    }
}
