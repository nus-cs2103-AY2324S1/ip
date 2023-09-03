package geraldbot.task;

/**
 * Represents an event task in the task list.
 */
public class Event extends Task {

    protected String start;
    protected String end;

    /**
     * Constructs an `Event` task with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the formatted file representation of the `Event` task.
     *
     * @return The file format representation of the task.
     */
    @Override
    public String fileFormat() {
        return "E " + super.fileFormat() + " | " + this.start + "-" + this.end;
    }

    /**
     * Returns the string representation of the `Event` task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " + "to: " + end + ")";
    }
}
