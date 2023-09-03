package cyrus.parser;

import java.util.HashMap;

import cyrus.commands.CommandType;

/**
 * Packet of information about the parsing response.
 *
 * <p>Modelled after CLI applications where the {@code argument} is the text before any options
 * and an {@code option} is the values of "flags".</p>
 */
public class ParseInfo {
    /**
     * Default {@code ParseInfo} for an empty command.
     */
    public static final ParseInfo EMPTY = new ParseInfo(
            CommandType.UNKNOWN,
            "",
            new HashMap<>()
    );

    private final CommandType commandType;
    private final String argument;
    private final HashMap<String, String> options;

    /**
     * Constructor for {@code Parser} parsed data.
     *
     * @param commandType type of command parsed.
     * @param argument    value of command outside of options.
     * @param options     key-value pairs associated with command.
     */
    public ParseInfo(CommandType commandType, String argument, HashMap<String, String> options) {
        this.commandType = commandType;
        this.argument = argument;
        this.options = options;
    }

    /**
     * Returns if parsed command has no argument.
     *
     * @return true if parsed command has no arguments
     */
    public boolean hasNoArgument() {
        return this.argument.equals("");
    }

    /**
     * Returns {@code argument} of the command.
     *
     * @return argument of command
     */
    public String getArgument() {
        return this.argument;
    }

    /**
     * Create a copy of the options to avoid accidentally modifying the current options map.
     *
     * @return Copy of options hashmap.
     */
    public HashMap<String, String> getOptions() {
        return new HashMap<>(this.options);
    }

    /**
     * Returns {@code commandType} of the command.
     *
     * @return command type
     */
    public CommandType getCommandType() {
        return this.commandType;
    }
}
