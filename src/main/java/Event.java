/**
 * Represents a task that has a start and end time.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Event extends Task {
    /** A string indicating start date / time of task */
    protected String start;
    /** A string indicating end date / time of task */
    protected String end;

    /**
     * A constructor to initialize the Event class.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the Event that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public String toStorageString() {
        return "E, " + isDone + ", " + description + ", " + start + ", " + end;
    }
}
