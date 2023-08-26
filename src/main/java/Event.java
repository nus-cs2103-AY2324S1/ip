/**
 * A task that has a starting and ending time.
 */
public class Event extends Task{
    private final String from;
    private final String to;

    /**
     * Creates an event task instance.
     *
     * @param description Description of the task.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    @Override
    public String toStringInFile() {
        return "[E]" + super.toStringInFile() + " " + this.from + " " + this.to;
    }
}
