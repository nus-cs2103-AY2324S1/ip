package ekud.command;

import ekud.util.DateTime;

/**
 * Represents the deadline command used to create a new task with a deadline.
 */
public final class CreateDeadlineCommand extends Command {
    private final String title;
    private final DateTime by;

    /**
     * Creates a new deadline command.
     * 
     * @param title The title of the task to create.
     * @param by    The deadline of the task.
     */
    public CreateDeadlineCommand(String title, DateTime by) {
        this.title = title;
        this.by = by;
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
     * Returns the deadline of the task to create.
     * 
     * @return The deadline.
     */
    public DateTime getBy() {
        return by;
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
        return String.format("deadline %s /by %s", title, by);
    }
}
