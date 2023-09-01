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
import ui.Ui;

/**
 * The Parser class. Handles the parsing of user commands
 * and returns the appropriate {@link Command} class.
 * Throws a {@link DukeException} when the user gives an
 * invalid command.
 */
public class Parser {

    /**
     * The method parses the user command, splitting it by
     * space and extracting the first part of the command.
     * It will match the first part to a set of allowable
     * commands and return the respective {@link Command} instance.
     * 
     * @param input The user command.
     * @return A {@link Command} instance created according to the user command.
     * @throws DukeException Thrown when the command given
     *                       is unrecognized.
     */
    public Command parse(String input) throws DukeException {
        // Ignore empty user input
        if (input.equals("")) {
            return new EmptyCommand();
        }

        // Extract main command first
        String command = input.split(" ")[0];

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
            throw new DukeException(new String[] {
                "Unrecognized command " + Ui.cTxt(command, Ui.Color.PURPLE),
                "Maybe create a new TODO with "
                        + Ui.cTxt("todo", Ui.Color.PURPLE)
                        + " read a book?"
            });
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
        return String.join(" ",
            Arrays.copyOfRange(
                item, 1, item.length
            )
        );
    }

    /**
     * Parses the content of a "mark" or "unmark" command.
     * Checks if a valid number is provided.
     * 
     * @param input The mark command.
     * @param type Indicates whether it is parsing 
     *             a mark or unmark command.
     * @return A {@link MarkCommand} instance.
     * @throws DukeException Thrown when no number is given.
     */
    private Command parseMarkCommand(String input, String type) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a number:",
                "Try " + Ui.cTxt("mark", Ui.Color.PURPLE) + " 1"
            });
        }

        return new MarkCommand(parseArr[1], type == "mark");
    }

    /**
     * Parses the content of a "todo" command.
     * Checks if a description was provided.
     * 
     * @param input The todo command.
     * @return A {@link TodoCommand} instance.
     * @throws DukeException Thrown when no description
     *                       is given.
     */
    private Command parseTodoCommand(String input) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("todo", Ui.Color.PURPLE) + " read a book"
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
     * @throws DukeException Thrown when no description
     *                       or invalid date is given.
     */
    private Command parseDeadlineCommand(String input) throws DukeException {
        // Split by the "/by" to separate the first and second part.
        String[] parseArr = input.split("/by ");

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");

        // Check if task descripton exists.
        if (header.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("deadline", Ui.Color.PURPLE)
                        + " submit essay /by Oct 10 2023 1600"
            });
        }

        // Check if a date was provided and the "/by" delimiter was supplied.
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a date:",
                "<- Remember to include /by ->",
                "Try " + Ui.cTxt("deadline", Ui.Color.PURPLE)
                        + " submit essay /by Oct 10 2023 1600"
            });
        }

        // Extract the date and add a new deadline to the task list.
        LocalDateTime date = DateParser.parseDateString(parseArr[1]);
        if (date == null) {
            throw new DukeException(new String[] {
                "Oops, looks like your date is in an invalid format...",
                "Here are some valid formats:",
                Ui.cTxt(
                    "2023-10-20, 20-10-2023, 2023/10/20, "
                            + "20/10/2023, Oct 10 2023, 10 Oct 2023",
                    Ui.Color.PURPLE
                ),
                "You can provide a timing as well: "
                        + Ui.cTxt("2023-10-20 1800", Ui.Color.YELLOW)
            });
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
     * @throws DukeException Thrown when no description
     *                       or invalid dates are given.
     */
    private Command parseEventCommand(String input) throws DukeException {
        // Split by "/from" to separate the first and (second + third) part.
        String[] parseArr = input.split("/from ");

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");

        // Check if task descripton exists.
        if (header.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("event", Ui.Color.PURPLE)
                        + " NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Check if /from exists.
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing " + Ui.cTxt("/from", Ui.Color.PURPLE),
                "Try " + Ui.cTxt("event", Ui.Color.PURPLE)
                        + " NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Split by "/to" to separate the second and third part.
        String[] dateParse = parseArr[1].split("/to ");

        // Check if /to exists.
        if (dateParse.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing " + Ui.cTxt("/to", Ui.Color.PURPLE),
                "Try " + Ui.cTxt("event", Ui.Color.PURPLE)
                        + " NUS carnival /from 21 Aug 2023 /to 22 Aug 2023"
            });
        }

        // Extract dates and validate them.
        LocalDateTime fromDate = DateParser.parseDateString(dateParse[0].strip());
        LocalDateTime toDate = DateParser.parseDateString(dateParse[1].strip());
        if (fromDate == null || toDate == null) {
            throw new DukeException(new String[] {
                "Oops, looks like your date is in an invalid format...",
                "Here are some valid formats:",
                Ui.cTxt(
                    "2023-10-20, 20-10-2023, 2023/10/20, "
                            + "20/10/2023, Oct 10 2023, 10 Oct 2023",
                    Ui.Color.PURPLE
                ),
                "You can provide a timing as well: "
                        + Ui.cTxt("2023-10-20 1800", Ui.Color.YELLOW)
            });
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
     * @throws DukeException Thrown when a number is not given.
     */
    private Command parseDeleteCommand(String input) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a number:",
                "Try " + Ui.cTxt("delete", Ui.Color.PURPLE) + " 1"
            });
        }

        return new DeleteCommand(parseArr[1]);
    }

    /**
     * Parses the find command.
     * Checks if a valid keyword was given.
     *
     * @param input The find command.
     * @return A {@Link FindCommand} instance
     * @throws DukeException Thrown when a keyword is not given.
     */
    private Command parseFindCommand(String input) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you didn't provide a keyword:",
                "Try " + Ui.cTxt("find", Ui.Color.PURPLE) + " read",
                "Or try " + Ui.cTxt("find", Ui.Color.PURPLE) + " read a book"
            });
        }

        return new FindCommand(extractTail(parseArr));
    }
}
