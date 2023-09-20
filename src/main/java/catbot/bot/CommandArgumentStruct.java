package catbot.bot;

/**
 * Simple container for a command and argument pair, both stored as Strings.
 */
public class CommandArgumentStruct {
    private final String command;
    private final String argument;

    /**
     * Constructor for a CommandArgumentStruct.
     *
     * @param command string that represents the command to run.
     * @param argument string that represents the argument for the command.
     */
    public CommandArgumentStruct(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    public String getCommand() {
        return command;
    }
}
