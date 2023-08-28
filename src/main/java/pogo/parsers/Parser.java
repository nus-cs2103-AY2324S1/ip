package pogo.parsers;

import pogo.commands.Command;
import pogo.commands.ExitCommand;
import pogo.commands.InvalidCommand;
import pogo.commands.ListTasksCommand;
import pogo.common.Messages;
import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Command parseCommand(String input) {
        final Matcher matcher = COMMAND_PATTERN.matcher(input.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(Messages.MESSAGE_INVALID_COMMAND);
        }

        final String commandWord = matcher.group("command");
        final String arguments = matcher.group("arguments").trim();

        try {
            switch (commandWord) {
            case "list":
                return new ListTasksCommand();
            case "deadline":
                return TaskParser.parseDeadlineCommand(arguments);
            case "todo":
                return TaskParser.parseToDoCommand(arguments);
            case "event":
                return TaskParser.parseEventCommand(arguments);
            case "mark":
                return TaskParser.parseMarkCommand(arguments, true);
            case "unmark":
                return TaskParser.parseMarkCommand(arguments, false);
            case "delete":
                return TaskParser.parseDeleteCommand(arguments);
            case "bye":
                return new ExitCommand();
            }
        } catch (PogoInvalidTaskException e) {
            return new InvalidCommand(Messages.INVALID_TASK);
        }

        return null;
    }
}
