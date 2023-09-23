package tasket.command;

/**
 * The class for command result.
 */
public class CommandResult {
    private final String response;
    private final boolean isExit;

    /**
     * Constructs a command result.
     *
     * @param response The response produced by the command.
     * @param isExit Is the command a bye command.
     */
    public CommandResult(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Get response message.
     *
     * @return The response message.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Check if the command is bye command.
     *
     * @return False for all commands except bye command.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
