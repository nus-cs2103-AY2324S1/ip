package slay.command;

import slay.Message;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Saying Goodbye to me.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(Message.MESSAGE_GOODBYE);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}