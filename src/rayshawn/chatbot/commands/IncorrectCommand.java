package rayshawn.chatbot.commands;

public class IncorrectCommand extends Command{
    private final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}
