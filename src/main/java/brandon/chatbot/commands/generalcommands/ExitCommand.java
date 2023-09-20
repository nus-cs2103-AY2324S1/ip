package brandon.chatbot.commands.generalcommands;

import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.CommandResult;

/**
 * Represents a command that exits from the duke program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String EXIT_MESSAGE = "bye bye...";
    @Override
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }
}
