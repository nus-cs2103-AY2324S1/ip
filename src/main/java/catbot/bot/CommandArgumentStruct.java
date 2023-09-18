package catbot.bot;


/**
 * Simple container for a command and argument pair, both stored as Strings.
 */
public class CommandArgumentStruct {
    private final String command;
    private final String argument;

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