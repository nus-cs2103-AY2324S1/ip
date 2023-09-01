/**
 * Represent a task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates an event task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     * @param start The start time that the user inputs
     * @param end The ending time that the user inputs
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task that could be done or undone.
     *
     * @param isDone Whether the task is done or undone
     * @param description The description of the task that the user inputs
     * @param start The start time that the user inputs
     * @param end The ending time that the user inputs
     */
    public Event(boolean isDone, String description, String start, String end) {
        super(isDone, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event task from the save format.
     *
     * @param formattedTask The string representation of the event task
     * @return An event task
     */
    public static Event createFromSaveFormat(String formattedTask) {
        String[] args = formattedTask.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String[] times = args[3].split("-");
        return new Event(isDone, args[2], times[0], times[1]);
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + this.start + "-" + this.end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.start + " to: " + this.end + ")";
    }
}
