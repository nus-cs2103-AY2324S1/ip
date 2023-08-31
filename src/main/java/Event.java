/**
 * Tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    //TODO
    static final String SYMBOL = "E";

    protected String from;
    protected String to;

    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    Event(String description, String period, boolean isDone) {
        super(description, isDone);
        this.from = period.split("-")[0];
        this.to = period.split("-")[1];
    }

    @Override
    public String getType() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + super.getTask() + " (from: " + this.from + " to:" + this.to + ")";
    }
}
