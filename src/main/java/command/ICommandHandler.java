package command;

public interface ICommandHandler {

    /**
     * This method will be called when the command is being executed.
     * @param command the full command
     * @param parameters the command parameters (splited by spaces)
     * @throws CommandException when any exception happens during command execution
     */
    public void execute(String command, String parameters[]) throws CommandException;
}
