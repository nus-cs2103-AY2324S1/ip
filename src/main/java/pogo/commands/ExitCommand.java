package pogo.commands;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    public ExitCommand() {
    }

    /**
     * Returns true if the command is an ExitCommand.
     * @param command Command to be checked.
     * @return true if the command is an ExitCommand.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Returns a CommandResult containing a goodbye message.
     * @return CommandResult containing a goodbye message.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }
}
