public class Event extends Task{
    private String from;
    private String to;

    /**
     * Create an event task instance.
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
     * String representation of the task.
     * @return Desired representation of the task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
