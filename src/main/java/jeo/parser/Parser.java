package jeo.parser;

import jeo.command.ByeCommand;
import jeo.command.Command;
import jeo.command.DeadlineCommand;
import jeo.command.DeleteCommand;
import jeo.command.EventCommand;
import jeo.command.FindCommand;
import jeo.command.ListCommand;
import jeo.command.MarkCommand;
import jeo.command.SortCommand;
import jeo.command.ToDoCommand;
import jeo.command.UnmarkCommand;
import jeo.exception.JeoException;

/**
 * Parses inputs from the user.
 *
 * @author Joseph Oliver Lim
 */
public class Parser {
    /**
     * Enum for the command words that can be interpreted.
     */
    enum CommandWord {
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        FIND,
        SORT,
        TODO,
        DEADLINE,
        EVENT,
    }

    /**
     * Parses Bye Command.
     *
     * @return A ByeCommand.
     */
    private static Command parseByeCommand() {
        return new ByeCommand();
    }

    /**
     * Parses Delete Command.
     *
     * @return A DeleteCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseDeleteCommand(String input) throws JeoException {
        return new DeleteCommand(Integer.parseInt(input.split(" ")[1]) - 1);
    }

    /**
     * Parses List Command.
     *
     * @return A ListCommand.
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parses Mark Command.
     *
     * @return A MarkCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseMarkCommand(String input) throws JeoException {
        return new MarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
    }

    /**
     * Parses Unmark Command.
     *
     * @return A UnmarkCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseUnmarkCommand(String input) throws JeoException {
        return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]) - 1);
    }

    /**
     * Parses Find Command.
     *
     * @return A FindCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseFindCommand(String input) throws JeoException {
        return new FindCommand(input.split(" ", 2)[1].trim());
    }

    /**
     * Parses Sort Command.
     *
     * @return A SortCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseSortCommand(String input) throws JeoException {
        return new SortCommand();
    }

    /**
     * Parses ToDo Command.
     *
     * @return A ToDoCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseToDoCommand(String input) throws JeoException {
        try {
            return new ToDoCommand(input.split(" ")[1].trim());
        } catch (Exception e) {
            throw new JeoException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Parses Deadline Command.
     *
     * @return A DeadlineCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseDeadlineCommand(String input) throws JeoException {
        try {
            String[] split = input.split(" ", 2)[1].trim().split(" /by ");
            return new DeadlineCommand(split[0], split[1]);
        } catch (Exception e) {
            throw new JeoException("OOPS!!! The description of the deadline is of wrong format.");
        }
    }

    /**
     * Parses Event Command.
     *
     * @return A EventCommand representing the user input.
     * @throws JeoException If the input is invalid.
     */
    private static Command parseEventCommand(String input) throws JeoException {
        try {
            String[] split = input.split(" ", 2)[1].trim().split(" /from | /to ");
            return new EventCommand(split[0], split[1], split[2]);
        } catch (Exception e) {
            throw new JeoException("OOPS!!! The description of the event is of wrong format.");
        }
    }

    /**
     * Parses the user input into a command.
     *
     * @param input The user input to be parsed.
     * @return A command representing the user input.
     * @throws JeoException If the input is invalid.
     */
    public static Command parse(String input) throws JeoException {
        try {
            CommandWord commandWord = CommandWord.valueOf(input.split(" ")[0].trim().toUpperCase());
            switch (commandWord) {
            case BYE:
                return parseByeCommand();
            case DELETE:
                return parseDeleteCommand(input);
            case LIST:
                return parseListCommand();
            case MARK:
                return parseMarkCommand(input);
            case UNMARK:
                return parseUnmarkCommand(input);
            case FIND:
                return parseFindCommand(input);
            case SORT:
                return parseSortCommand(input);
            case TODO:
                return parseToDoCommand(input);
            case DEADLINE:
                return parseDeadlineCommand(input);
            case EVENT:
                return parseEventCommand(input);
            default:
                throw new JeoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new JeoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
