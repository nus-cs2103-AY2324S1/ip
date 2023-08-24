package command;

public interface ICommandHandler {
    public void execute(String command, String parameters[]) throws CommandException;
}
