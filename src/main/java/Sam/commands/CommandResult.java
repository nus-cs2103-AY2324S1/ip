package sam.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    public final String feedbackToUser;

    public CommandResult(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(arg).append("\n");
        }
        this.feedbackToUser = stringBuilder.toString();
    }

    /**
     * Returns message relevant to the command result, if any.
     */
    public String getFeedback() {
        return this.feedbackToUser;
    }

}
