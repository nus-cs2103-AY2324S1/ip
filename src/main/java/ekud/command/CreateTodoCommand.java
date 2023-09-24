package ekud.command;

/**
 * Represents the todo command used to create a new simple task.
 */
public final class CreateTodoCommand extends Command {
    private final String title;

    /**
     * Creates a new todo command.
     * 
     * @param title The title of the task to create.
     */
    public CreateTodoCommand(String title) {
        this.title = title;
    }

    /**
     * Returns the title of the task to create.
     * 
     * @return The title.
     */
    public String getTitle() {
        return title;
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
        return String.format("todo %s", title);
    }
}
