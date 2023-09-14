package pogo.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pogo.commands.AddDeadlineCommand;
import pogo.commands.AddEventCommand;
import pogo.commands.AddToDoCommand;
import pogo.commands.Command;
import pogo.commands.DeleteTaskCommand;
import pogo.commands.ExitCommand;
import pogo.commands.FindTaskCommand;
import pogo.commands.InvalidCommand;
import pogo.commands.ListTasksCommand;
import pogo.commands.MarkTaskCommand;
import pogo.commands.UnmarkTaskCommand;
import pogo.common.Messages;
import pogo.tasks.exceptions.PogoInvalidTaskException;

/**
 * The Parser class is responsible for parsing user input.
 * Based on AddressBook2
 */
public class Parser {
    /**
     * The pattern used to parse commands.
     * A command consists of a command word e.g. "list" and an optional arguments string.
     */
    public static final Pattern COMMAND_PATTERN = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param input the user input to parse.
     * @return Command to be executed.
     */
    public static Command parseCommand(String input) {
        final Matcher matcher = COMMAND_PATTERN.matcher(input.trim());
        boolean isValidCommand = matcher.matches();
        if (!isValidCommand) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String commandWord = matcher.group("command");
        final String arguments = matcher.group("arguments").trim();

        try {
            switch (commandWord) {
            case ListTasksCommand.COMMAND_WORD:
                return TaskParser.parseListCommand(arguments);
            case FindTaskCommand.COMMAND_WORD:
                return new FindTaskCommand(arguments);
            case AddDeadlineCommand.COMMAND_WORD:
                return TaskParser.parseDeadlineCommand(arguments);
            case AddToDoCommand.COMMAND_WORD:
                return TaskParser.parseToDoCommand(arguments);
            case AddEventCommand.COMMAND_WORD:
                return TaskParser.parseEventCommand(arguments);
            case MarkTaskCommand.COMMAND_WORD:
                return TaskParser.parseMarkCommand(arguments, true);
            case UnmarkTaskCommand.COMMAND_WORD:
                return TaskParser.parseMarkCommand(arguments, false);
            case DeleteTaskCommand.COMMAND_WORD:
                return TaskParser.parseDeleteCommand(arguments);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            default:
                assert false : "Command word should be valid";
                break;
            }
        } catch (PogoInvalidTaskException e) {
            return new InvalidCommand(Messages.INVALID_TASK);
        }

        return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
    }

}
