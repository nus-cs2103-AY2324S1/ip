package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.PrintDateCommand;
import duke.exception.DeadlineException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.ManipulateException;
import duke.exception.PrintDateException;
import duke.exception.TodoException;

/**
 * Represents a parser that parses user input and turn it into command
 * that the chatbot can execute.
 * <p><br>Commands are in the form: {@code <keyword> <command body>}</p><br>
 * <p>Where the command body is optional. The keyword is case-insensitive.
 * The command body is the rest of the user input after the keyword.
 * If the command body is present, it must be separated from the keyword by a space.</p>
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param fullCommand The user input.
     * @return The corresponding command.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ");
        if (fullCommand.isEmpty() || split.length == 0) {
            throw new DukeException("OOPS!!! You have not entered anything!");
        }

        Keyword key;
        try {
            key = Keyword.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
        }

        if (split.length == 1) {
            return parseOneWordCommand(key);
        }
        String commandBody = fullCommand.substring(split[0].length() + 1);
        return parseMultiWordCommand(key, commandBody);
    }

    /**
     * Parses user input that only contains one word, and
     * returns the corresponding command.
     *
     * @param key The keyword of the command.
     * @return The corresponding command.
     * @throws DukeException If the user input is invalid.
     */
    private static Command parseOneWordCommand(Keyword key) throws DukeException {
        String err = String.format("OOPS!!! The description of a %s cannot be empty.", key.getKeyword());
        switch (key) {
        case BYE:
            return new ExitCommand();

        case LIST:
            return new ListCommand();

        case TODO:
            throw new TodoException(err);

        case DEADLINE:
            throw new DeadlineException(err);

        case EVENT:
            throw new EventException(err);

        case MARK:
        case UNMARK:
        case DELETE:
            throw new ManipulateException(err, key.getKeyword());

        default: // Equivalent to case PRINT_DATE
            throw new PrintDateException(err);
        }
    }

    /**
     * Parses user input that contains more than one word, and
     * returns the corresponding command.
     *
     * @param key The keyword of the command.
     * @param commandBody The main body of the command.
     * @return The corresponding command.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parseMultiWordCommand(Keyword key, String commandBody) throws DukeException {
        switch (key) {
        case BYE:
            if (commandBody.equals(Ui.NAME)) {
                return new ExitCommand();
            }
            // fall through
        case LIST:
            String errMessage = Ui.connectTwoLine(
                    String.format("OOPS!!! The command for %s is invalid.", key.getKeyword()),
                    String.format("Enter in the form: \"%s\"", key.getKeyword()));
            throw new DukeException(errMessage);

        case MARK:
        case UNMARK:
            return new MarkCommand(key, commandBody);

        case DELETE:
            return new DeleteCommand(commandBody);

        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(key, commandBody);

        default: // Equivalent to case PRINT_DATE
            return new PrintDateCommand(commandBody);
        }
    }
}
