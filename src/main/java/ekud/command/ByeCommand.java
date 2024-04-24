package ekud.command;

/**
 * Represents the bye command used to exit the program.
 */
public final class ByeCommand extends Command {
    /**
     * Creates a new bye command.
     */
    public ByeCommand() {
    }

    /**
     * Returns the string representation of the command.
     * This is identical (excluding whitespace) to how the user would type it into
     * the CLI.
     * 
     * @return String representation.
     */
    @Override
    public String toString() {
        return "bye";
    }
}
