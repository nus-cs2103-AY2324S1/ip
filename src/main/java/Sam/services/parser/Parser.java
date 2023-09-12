package sam.services.parser;

import sam.commands.AddEventCommand;
import sam.commands.Command;
import sam.commands.HelpCommand;
import sam.commands.IncorrectCommand;
import sam.commands.AddDeadlineCommand;
import sam.commands.AddToDoCommand;
import sam.commands.DeleteTaskCommand;
import sam.commands.MarkTaskCommand;
import sam.commands.UnmarkTaskCommand;
import sam.commands.ListCommand;
import sam.commands.FindCommand;
import sam.commands.ExitCommand;
import sam.exceptions.DukeException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static sam.constants.Message.INVALID_COMMAND_FORMAT;
import static sam.services.parser.TaskParser.prepareAddDeadline;
import static sam.services.parser.TaskParser.prepareAddEvent;
import static sam.services.parser.TaskParser.prepareAddTodo;
import static sam.services.parser.TaskParser.prepareFind;

/**
 * Parses user input.
 * Based on AddressBook2
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(arguments);

        case AddToDoCommand.COMMAND_WORD:
            return prepareAddTodo(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }


    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteTaskCommand(targetIndex);
        } catch (DukeException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT + "\n" + pe.getMessage(),
                    DeleteTaskCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the mark task as done command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareMark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkTaskCommand(targetIndex);
        } catch (DukeException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT + "\n" + pe.getMessage(),
                    MarkTaskCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the mark task as not done command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareUnmark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkTaskCommand(targetIndex);
        } catch (DukeException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT + "\n" + pe.getMessage(),
                    UnmarkTaskCommand.MESSAGE_USAGE));
        }
    }


    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws DukeException if no region of the args string could be found for the index
     */
    private int parseArgsAsDisplayedIndex(String args) throws DukeException {
        final Pattern TASK_INDEX_PATTERN = Pattern.compile("(?<index>\\d+)");
        final Matcher matcher = TASK_INDEX_PATTERN.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Could not find index number to parse");
        }
        try {
            return Integer.parseInt(matcher.group("index")) - 1;
        } catch (Exception e) {
            throw new DukeException("Could not parse index number");
        }
    }

}
