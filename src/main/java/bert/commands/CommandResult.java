package bert.commands;

/**
 * Represents the result of a command after its execution.
 */
public class CommandResult {
    private final String feedbackToUser;

    /**
     * Constructs a CommandResult instance containing the user feedback
     * following the execution of a command.
     *
     * @param feedbackToUser A user message about the result of executing a command.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Gets the string representation of the user feedback.
     *
     * @return The string representation of the user feedback.
     */
    public String getFeedbackToUser() {
        return this.feedbackToUser;
    }
}
