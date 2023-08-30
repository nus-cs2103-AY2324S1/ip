package cyrus.parser;

import cyrus.commands.CommandType;

import java.util.HashMap;

public class ParseInfo {
    public final static ParseInfo EMPTY = new ParseInfo(
            CommandType.UNKNOWN,
            "",
            new HashMap<>()
    );

    public final CommandType commandType;
    private final String argument;
    private final HashMap<String, String> options;

    public ParseInfo(CommandType commandType, String argument, HashMap<String, String> options) {
        this.commandType = commandType;
        this.argument = argument;
        this.options = options;
    }

    public boolean hasNoArgument() {
        return this.argument.equals("");
    }

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
}
