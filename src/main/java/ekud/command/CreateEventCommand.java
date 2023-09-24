package ekud.command;

import ekud.util.DateTime;

/**
 * Represents the event command used to create a new task with a start and end
 * date/time.
 */
public final class CreateEventCommand extends Command {
    private final String title;
    private final DateTime from;
    private final DateTime to;

    /**
     * Creates a new event command.
     * 
     * @param title The title of the task to create.
     * @param from  The start date/time of the task.
     * @param to    The end date/time of the task.
     */
    public CreateEventCommand(String title, DateTime from, DateTime to) {
        this.title = title;
        this.from = from;
        this.to = to;
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
     * Returns the start date/time of the task to create.
     * 
     * @return The start date/time.
     */
    public DateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of the task to create.
     * 
     * @return The end date/time.
     */
    public DateTime getTo() {
        return to;
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
        return String.format("event %s /from %s /to %s", title, from, to);
    }
}
