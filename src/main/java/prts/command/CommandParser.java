package prts.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import prts.task.Deadline;
import prts.task.Event;
import prts.task.Todo;

/**
 * The parser used to convert arbitrary user input into definite commands executable by PRTS.
 * Specific keywords are required to signal to the CommandParser what command is input, and they must be
 * the first word input in the line.
 */
public class CommandParser {

    /**
     * Parses the user input, returning a Command.
     * Recognizes predefined keywords if they occur at the start of the user input.
     * Certain commands must follow predefined syntax to be parsed correctly.
     * Whitespace is stripped and will not affect parsing.
     * @param fullCommand The raw user input received from the UI.
     * @return A Command that can be executed by PRTS.
     * @throws ParsingException If an error is encountered when parsing user input.
     */
    public static Command parse(String fullCommand) throws ParsingException {

        // Splits the input to extract the user's primary command
        // We cannot use splitAndStrip here as a single-word command is not improper format
        String[] processedCommand = fullCommand.strip().split(" ", 2);
        String command = processedCommand[0].toLowerCase().strip();
        String detail = processedCommand.length == 2 ? processedCommand[1].strip() : "";

        switch (command) {

        case "bye":
            return new ExitCommand();
        case "todo":
            return addTodo(detail);
        case "deadline":
            return addDeadline(detail);
        case "event":
            return addEvent(detail);
        case "mark":
            return markTask(detail);
        case "unmark":
            return unMarkTask(detail);
        case "list":
            return listTasks(detail);
        case "delete":
            return deleteTask(detail);
        case "find":
            return findTerm(detail);
        case "undo":
            return undoCommand(detail);
        case "thanks":
            return new MessageCommand("...No problem.");
        case "zzz":
            return new DocRestCommand();
        case "hi":
            return new MessageCommand("Greetings.");
        default:
            return new MessageCommand("Sorry, I didn't quite catch that. Are you having enough sleep?");
        }
    }

    /**
     * Splits a given string by a given regex, stripping the outputs of leading and trailing
     * whitespace. The string is only split once, at the first instance of the regex.
     * In the case of a non-existent regex, an IMPROPER_FORMAT ParsingException is thrown.
     * @param string The string to split
     * @param regex The regex to split by
     * @return A String array of size 2, with index 0 containing the portion before the regex
     *         and index 1 containing the portion after the regex.
     */
    private static String[] splitAndStrip(String string, String regex) throws ParsingException {
        String[] splits = string.strip().split(regex, 2);
        String beforeRegex = splits[0].strip();
        if (splits.length == 2) {
            String afterRegex = splits[1].strip();
            return new String[] {beforeRegex, afterRegex};
        } else {
            throw new ParsingException(ParsingException.ExceptionType.IMPROPER_FORMAT);
        }
    }

    /**
     * Creates an AddCommand that adds a To-do to the TaskList, based on user input.
     * @param detail The description of the To-do.
     * @return An AddCommand that will add the desired To-do to the list.
     * @throws ParsingException If the To-do is not supplied with a description.
     */
    private static AddCommand addTodo(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DESCRIPTION);
        }

        return new AddCommand(new Todo(detail));
    }

    /**
     * Creates an AddCommand that adds a Deadline to the TaskList, based on user input.
     * @param detail The details relevant to the Deadline.
     * @return An AddCommand that will add the desired Deadline to the list.
     * @throws ParsingException If either the description or the deadline of the Deadline are not
     *                          supplied.
     */
    private static AddCommand addDeadline(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DESCRIPTION);
        }

        String[] processedDeadline = splitAndStrip(detail, "/by");
        if (processedDeadline[0].isBlank() || processedDeadline[1].isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DESCRIPTION);
        }
        try {
            LocalDateTime deadlineDate = LocalDateTime.parse(processedDeadline[1]);
            return new AddCommand(new Deadline(processedDeadline[0], deadlineDate));
        } catch (DateTimeParseException e) {
            return new AddCommand(new Deadline(processedDeadline[0], processedDeadline[1]));
        }
    }

    /**
     * Creates an AddCommand that adds an Event to the TaskList, based on user input.
     * @param detail The details relevant to the Event.
     * @return An AddCommand that will add the desired Event to the list.
     * @throws ParsingException If either the description or the dates of the Event are not
     *                          supplied.
     */
    private static AddCommand addEvent(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DESCRIPTION);
        }
        String[] processedEventDescription = splitAndStrip(detail, "/from");
        String[] processedEventDates = splitAndStrip(processedEventDescription[1], "/to");
        if (processedEventDescription[0].isBlank() || processedEventDates[0].isBlank()
                || processedEventDates[1].isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DETAIL);
        }

        System.out.println("Roger that. Preparations will be underway.");
        try {
            LocalDateTime eventStartDate = LocalDateTime.parse(processedEventDates[0]);
            LocalDateTime eventEndDate = LocalDateTime.parse(processedEventDates[1]);
            return new AddCommand(new Event(processedEventDescription[0], eventStartDate, eventEndDate));
        } catch (DateTimeParseException e) {
            return new AddCommand(new Event(processedEventDescription[0], processedEventDates[0],
                    processedEventDates[1]));
        }
    }

    /**
     * Creates a MarkCommand based on user input.
     * @param detail The index of the task to be marked. Allows for the string "all" to mark all tasks.
     * @return A MarkCommand as specified by the user.
     * @throws ParsingException If the provided string is neither "all" nor parseable as an integer.
     */
    private static MarkCommand markTask(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);
        }

        if (detail.equalsIgnoreCase("all")) {
            return new MarkCommand(null);
        }

        try {
            int index = Integer.parseInt(detail);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
        }
    }

    /**
     * Creates an UnmarkCommand based on user input.
     * @param detail The index of the task to be unmarked. Allows for the string "all" to unmark all tasks.
     * @return An UnmarkCommand as specified by the user.
     * @throws ParsingException If the provided string is neither "all" nor parseable as an integer.
     */
    private static UnmarkCommand unMarkTask(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);
        }

        if (detail.equalsIgnoreCase("all")) {
            return new UnmarkCommand(null);
        }

        try {
            int index = Integer.parseInt(detail);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
        }
    }

    /**
     * Creates a ListCommand.
     * @param detail Unused in command creation, used only to identify if the user has formatted the
     *               command properly. List should only be called with no further input.
     * @return A ListCommand if the detail is blank.
     * @throws ParsingException If the detail is nonempty, indicating that there is excess input and thus
     *                          the command has not been formatted properly.
     */
    private static ListCommand listTasks(String detail) throws ParsingException {
        if (detail.isBlank()) {
            return new ListCommand();
        } else {
            throw new ParsingException(ParsingException.ExceptionType.EXCESS_INPUT);
        }
    }

    /**
     * Creates a DeleteCommand based on user input.
     * @param detail The index of the task to be deleted. Allows for the string "all" to delete all tasks.
     * @return A DeleteCommand as specified by the user.
     * @throws ParsingException If the provided string is neither "all" nor parseable as an integer.
     */
    private static DeleteCommand deleteTask(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_INDEX);
        }

        if (detail.equalsIgnoreCase("all")) {
            return new DeleteCommand(null);
        }

        try {
            int index = Integer.parseInt(detail);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
        }
    }

    /**
     * Creates a FindCommand based on user input.
     * @param detail The search term(s) the user inputs.
     * @return A FindCommand specific to that search string.
     * @throws ParsingException If the search string(s) are missing or unspecified.
     */
    private static FindCommand findTerm(String detail) throws ParsingException {
        if (detail.isBlank()) {
            throw new ParsingException(ParsingException.ExceptionType.MISSING_DESCRIPTION);
        }

        return new FindCommand(detail.toLowerCase());
    }

    /**
     * Creates an UndoCommand given the details provided by the user.
     * @param detail The number of commands to undo. The string "all" is accepted to undo all commands.
     * @return An UndoCommand based on the user input.
     * @throws ParsingException If the user input is not the string "all", and is not parseable as an
     *                          integer.
     */
    private static UndoCommand undoCommand(String detail) throws ParsingException {
        if (detail.isBlank()) {
            return new UndoCommand(1);
        }

        if (detail.equalsIgnoreCase("all")) {
            return new UndoCommand(null);
        }

        try {
            int index = Integer.parseInt(detail);
            return new UndoCommand(index);
        } catch (NumberFormatException e) {
            throw new ParsingException(ParsingException.ExceptionType.NOT_A_NUMBER);
        }
    }
}
