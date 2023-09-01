package sisyphus.task;

public class Event extends Task {
    protected String start, end;

    /**
     * Constructor when provided description, start and end time.
     *
     * @param description
     * @param start
     * @param end
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor when provided description, isDone state, start and end time.
     *
     * @param description
     * @param isDone
     * @param start
     * @param end
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns string representation.
     *
     * @return string representation with status icon, start and end time.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " +  this.description + " (from: " + this.start + " to: " + this.end + ")";
    }

    /**
     * Returns string representation used for saveFormat which is csv.
     *
     * @return string representation with comma as separator.
     */
    @Override
    public String toSaveFormat() {
        return String.format("E,%s,%s,%s,%s", description, isDone ? "1" : "0", start, end);
    }
}
