public class Event extends Task {

    /** Start time of event. */
    protected String start;
    /** End time of event. */
    protected String end;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getStorageDescription() {
        String isCompleted = this.isDone ? "1" : "0";
        return "E " + isCompleted + " " + this.description + "/from" + this.start + "/to" + this.end;
    }

    /**
     * Display string representation of an event.
     *
     * @return String representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }
}
