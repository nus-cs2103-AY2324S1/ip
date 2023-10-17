package duke.command;

/**
 * Represents an invalid command with a message to be shown to the user.
 */
public class InvalidCommand extends Command {
    public final String[] message;

    public InvalidCommand(String... message) {
        this.message = message;
    }

    /**
     * Returns the invalid command message as the user response.
     *
     * @return the response to the user
     */
    public String[] execute() {
        return message;
    }
}
