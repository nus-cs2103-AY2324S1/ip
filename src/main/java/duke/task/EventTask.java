package duke.task;
/**
 * Represents a task that has a specific start and end date.
 */
public class EventTask extends Task {
    private String fromDate;
    private String toDate;

    /**
     * Constructs an EventTask object with the specified description and event duration.
     * @param description The description of the event task.
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
     */
    public EventTask(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Overrides the default toString() method and returns a string representation
     *         of the object, including additional information about the event's start and end dates.
     * @return The method is returning a string representation of an event task. The string includes the
     *         task type "[E]", the task description (obtained from the superclass's toString() method), and the
     *                 event duration (specified by the "fromDate" and "toDate" variables).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
