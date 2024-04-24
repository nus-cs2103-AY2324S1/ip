package ekud.command;

/**
 * Represents the list command used to list all the tasks.
 */
public final class ListCommand extends Command {
    /**
     * Creates a new list command.
     */
    public ListCommand() {
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
        return "list";
    }
}
