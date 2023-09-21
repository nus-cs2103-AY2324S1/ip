package duke.command;

//@@author samuelim01-reused
// Reused from Addressbook Level 3 with minor modifications.

/**
 * Represents the result of a command execution
 */
public class CommandResult {
    private final String responseToUser;

    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult} with the specified fields
     * @param responseToUser
     * @param isExit
     */
    public CommandResult(String responseToUser, boolean isExit) {
        this.responseToUser = responseToUser;
        this.isExit = isExit;
    }

    public CommandResult(String responseToUser) {
        this(responseToUser, false);
    }

    public String getResponseToUser() {
        return responseToUser;
    }

    public boolean isExit() {
        return isExit;
    }
}

//@@author
