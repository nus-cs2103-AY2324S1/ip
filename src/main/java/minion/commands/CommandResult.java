package minion.commands;

/**
 * Represents a command result.
 */
public class CommandResult {
    private final String feedbackToUser;
    private boolean isExit;

    /**
     * Constructs a command result.
     * @param feedbackToUser feedback to user.
     * @param isExit whether the command is an exit one.
     */
    public CommandResult(String feedbackToUser, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isExit = isExit;
    }

    /**
     * Constructs a command result.
     * @param feedbackToUser feedback to user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false);
    }

    /**
     * Returns the feedback to user.
     * @return feedback to user.
     */
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    /**
     * Checks whether the command is exit.
     * @return true if command is exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
