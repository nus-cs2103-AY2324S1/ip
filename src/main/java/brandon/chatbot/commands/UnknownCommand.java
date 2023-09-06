package brandon.chatbot.commands;

public class UnknownCommand extends Command {
    @Override
    public CommandResult execute() {
        return new CommandResult("I am not intelligent enough to understand what that means...");
    }
}
