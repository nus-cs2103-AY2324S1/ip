package Tasks;


public class Event extends Task {
    /**
     * A child class of Tasks to create tasks with a start time & end time.
     */

    protected String start;
    protected String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
