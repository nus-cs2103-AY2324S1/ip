package pogo.commands;

import pogo.common.Messages;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Returns a CommandResult containing a goodbye message.
     *
     * @return CommandResult containing a goodbye message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(Messages.EXIT_MESSAGE);
    }
}
