public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * The constructor for the Event class.
     *
     * @param name Name of the event task.
     * @param startDate Start date of the event.
     * @param endDate End date of the event.
     */
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.name + " (from: " + this.startDate + " to: " + this.endDate + ")";
        } else {
            return "[E][ ] " + this.name + " (from: " + this.startDate + " to: " + this.endDate + ")";
        }
    }
}
