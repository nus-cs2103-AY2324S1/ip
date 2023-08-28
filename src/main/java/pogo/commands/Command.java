package pogo.commands;

/**
 * The Command class is the base class for all commands.
 */
public class Command {
    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     *
     * @return The result of the command.
     */
    public CommandResult execute() {
        return new CommandResult();
    }
}
