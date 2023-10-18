package parser;

import java.time.LocalDateTime;
import java.util.Arrays;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EmptyCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import common.DateParser;

import data.exception.DukeException;
import data.exception.InvalidDateParamException;
import data.exception.InvalidParamException;
import data.exception.UnrecognizedCommandException;

/**
 * The Parser class. Handles the parsing of user commands
 * and returns the appropriate {@link Command} class.
 */
public class Parser {

    /**
     * The method parses the user command and extracts
     * the first part of the command.
     * It will match it to a set of allowable commands
     * and return the respective {@link Command} instance.
     *
     * @param input The user command.
     * @return A {@link Command} instance created according to the user command.
     * @throws DukeException Thrown when the command given
     *                       is unrecognized.
     */
    public Command parse(String input) throws DukeException {
        String strippedInput = input.strip().trim();
        // Ignore empty user input
        if (strippedInput.equals("")) {
            return new EmptyCommand();
        }

        // Extract main command first
        String command = strippedInput.split(" ")[0];

        // Parse main command
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(input, "mark");
        case "unmark":
            return parseMarkCommand(input, "unmark");
        case "todo":
            return parseTodoCommand(input);
        case "deadline":
            return parseDeadlineCommand(input);
        case "event":
            return parseEventCommand(input);
        case "delete":
            return parseDeleteCommand(input);
        case "find":
            return parseFindCommand(input);
        default:
            throw new UnrecognizedCommandException(command);
        }
    }

    /**
     * A method to extract and join the elements from index 1 to the end.
     * Used for situations where it is needed to extract 
     * "read a book" from "todo read a book".
     *
     * @param item The array containing the contents to be extracted.
     * @return A string that contains the extracted contents 
     *         joined by empty space.
     */
    private static String extractTail(String[] item) {
        String[] tail = Arrays.copyOfRange(
                item, 1, item.length
        );
        assert item.length - tail.length == 1
                : "length of tail should be one less than item passed in";
        return String.join(" ", tail);
    }

    /**
     * Parses the content of a "mark" or "unmark" command.
     * Checks if a valid number is provided.
     *
     * @param input The mark command.
     * @param type Indicates whether it is parsing
     *             a mark or unmark command.
     * @return A {@link MarkCommand} instance.
     * @throws InvalidParamException Thrown when no number is given.
     */
    private Command parseMarkCommand(String input, String type) throws InvalidParamException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a number:",
                "Try mark 1"
            });
        }

        return new MarkCommand(parseArr[1], type.equals("mark"));
    }

    /**
     * Parses the content of a "todo" command.
     * Checks if a description was provided.
     *
     * @param input The todo command.
     * @return A {@link TodoCommand} instance.
     * @throws InvalidParamException Thrown when no description
     *                               is given.
     */
    private Command parseTodoCommand(String input) throws InvalidParamException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a description:",
                "Try todo read a book"
            });
        }

        return new TodoCommand(extractTail(parseArr));
    }

    /**
     * Parses the content of a "deadline" command.
     * Checks if a description was provided
     * and if a valid date was supplied using /by.
     *
     * @param input The deadline command.
     * @return A {@link DeadlineCommand} instance.
     * @throws InvalidParamException     Thrown when no description
     *                                   is given.
     * @throws InvalidDateParamException Thrown when an invalid
     *                                   date is given.
     */
    private Command parseDeadlineCommand(String input)
            throws InvalidParamException, InvalidDateParamException {
        // Split by the "/by" to separate the first and second part.
        String[] parseArr = input.split("/by ");

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");

        // Check if task description exists.
        if (header.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a description:",
                "Try deadline "
                        + "submit essay /by Oct 10 2023 1600"
            });
        }

        // Check if a date was provided and the "/by" delimiter was supplied.
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a date:",
                "<- Remember to include /by ->",
                "Try deadline "
                        + "submit essay /by Oct 10 2023 1600"
            });
        }

        // Extract the date and add a new deadline to the task list.
        LocalDateTime date = DateParser.parseDateString(parseArr[1]);
        if (date == null) {
            throw new InvalidDateParamException();
        }
        return new DeadlineCommand(
                extractTail(header),
                date
        );
    }

    /**
     * Parses the "event" command.
     * Checks if a description was provided
     * and valid dates supplied with /from and /to.
     *
     * @param input The event command.
     * @return An {@link EventCommand} instance.
     * @throws InvalidParamException     Thrown when no description
     *                                   is given.
     * @throws InvalidDateParamException Thrown when an invalid
     *                                   date is given.
     */
    private Command parseEventCommand(String input)
            throws InvalidParamException, InvalidDateParamException {
        // Split by "/from" to separate the first and (second + third) part.
        String[] parseArr = input.split("/from ");

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");

        // Check if task descripton exists.
        if (header.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a description:",
                "Try event "
                        + "NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Check if /from exists.
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing /from",
                "Try event "
                        + "NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Split by "/to" to separate the second and third part.
        String[] dateParse = parseArr[1].split("/to ");

        // Check if /to exists.
        if (dateParse.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing /to",
                "Try event "
                        + "NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Extract dates and validate them.
        LocalDateTime fromDate = DateParser.parseDateString(dateParse[0].strip());
        LocalDateTime toDate = DateParser.parseDateString(dateParse[1].strip());
        if (fromDate == null || toDate == null) {
            throw new InvalidDateParamException();
        }
        return new EventCommand(
                extractTail(header),
                fromDate,
                toDate
        );
    }

    /**
     * Parses the delete command.
     * Checks if a valid number was given.
     *
     * @param input The delete command.
     * @return A {@link DeleteCommand} instance.
     * @throws InvalidParamException Thrown when a number is not given.
     */
    private Command parseDeleteCommand(String input) throws InvalidParamException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you're missing a number:",
                "Try delete 1"
            });
        }

        return new DeleteCommand(parseArr[1]);
    }

    /**
     * Parses the find command.
     * Checks if a valid keyword was given.
     *
     * @param input The find command.
     * @return A {@link FindCommand} instance
     * @throws InvalidParamException Thrown when a keyword is not given.
     */
    private Command parseFindCommand(String input) throws InvalidParamException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new InvalidParamException(new String[] {
                "Looks like you didn't provide a keyword:",
                "Try find read",
                "Or try find read a book"
            });
        }

        return new FindCommand(extractTail(parseArr));
    }
}
