/**
 * The Events class extends the Task class.
 * It contains a description, start time, end time, and inherits the
 * completion status functionality from the Task class.
 */
public class Events extends Task {
    protected String start;
    protected String end;

    /**
     * Constructs an Events object with the given description, start time, and end time.
     *
     * @param description the description of the Events task
     * @param start the start time of the Events task
     * @param end the end time of the Events task
     */
    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Events task,
     * including its task type indicator, description, start time, and end time.
     *
     * @return a string representation of the Events task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}