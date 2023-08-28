package pogo.commands;

/**
 * Represents and invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * The feedback message to be shown to the user.
     */
    public final String feedbackToUser;

    /**
     * Creates a new InvalidCommand object.
     *
     * @param feedbackToUser The feedback message to be shown to the user.
     */
    public InvalidCommand(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}
