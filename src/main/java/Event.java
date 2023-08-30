public class Event extends Task {
    /** Start time of Event. */
    protected String start;
    /** End time of Event. */
    protected String end;

    /** Constructor for Event.
     *
     * @param description Description of task.
     * @param start Start time of Event.
     * @param end End time of Event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /** toString method for Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: "+ end + ")";
    }
}
