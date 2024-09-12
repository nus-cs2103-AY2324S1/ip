package rayshawn.chatbot.commands;

/**
 * Represents invalid commands not used by the chatbot.
 */
public class NoSuchCommand extends Command {
    private static final String MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE);
    }
}
