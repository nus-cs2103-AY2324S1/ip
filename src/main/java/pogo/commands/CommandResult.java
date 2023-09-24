package pogo.commands;

/**
 * Encapsulates the result of a command execution.
 * Based on AddressBook-Level2
 */
public class CommandResult {
    /**
     * The feedback message to be shown to the user.
     */
    private final String feedbackToUser;

    /**
     * Constructs a {@code CommandResult} with the specified feedback.
     * @param feedbackToUser
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Returns the feedback message to be shown to the user.
     * @return The feedback message.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
