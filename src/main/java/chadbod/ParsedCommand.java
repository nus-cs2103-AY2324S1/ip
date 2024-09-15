package chadbod;

/**
 * The ParsedCommand class represents a parsed user command, containing the command type and its details.
 */
public class ParsedCommand {
    private final Command command;
    private final String details;

    /**
     * Constructs an instance of the ParsedCommand class.
     *
     * @param command The parsed command type.
     * @param details The details associated with the command.
     */
    public ParsedCommand(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    /**
     * Returns the command type associated with the parsed command.
     *
     * @return The command type associated with the parsed command.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the details associated with the command.
     *
     * @return The command details.
     */
    public String getDetails() {
        return this.details;
    }
}
