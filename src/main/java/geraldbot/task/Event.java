package geraldbot.task;

/**
 * Represents an event task in the task list.
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;

    /**
     * Constructs an `Event` task with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param startTime       The start time of the event.
     * @param endTime         The end time of the event.
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the formatted file representation of the `Event` task.
     *
     * @return The file format representation of the task.
     */
    @Override
    public String fileFormat() {
        return "E " + super.fileFormat() + " | " + this.startTime + "-" + this.endTime;
    }

    /**
     * Returns the string representation of the `Event` task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " " + "to: " + endTime + ")";
    }
}
