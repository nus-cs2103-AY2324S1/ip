package pogo.parsers;

import pogo.commands.Command;
import pogo.commands.InvalidCommand;
import pogo.common.Messages;

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

        return null;
    }
}
