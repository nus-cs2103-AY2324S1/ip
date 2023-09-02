package duke.command;

/**
 * Represents an invalid command with a message to be shown to the user.
 */
public class InvalidCommand extends Command {
    public final String[] message;

    public InvalidCommand(String... message) {
        this.message = message;
    }

    public String[] execute() {
        return message;
    }
}
