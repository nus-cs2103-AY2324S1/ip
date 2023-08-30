package rayshawn.chatbot.commands;

/**
 * Represents an incorrect command format, produces feedback to user.
 */
public class IncorrectCommand extends Command{
    public final String feedbackToUser;

    /**
     * Constructor for IncorrectCommand.
     *
     * @param feedbackToUser message to the user.
     */
    public IncorrectCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}
