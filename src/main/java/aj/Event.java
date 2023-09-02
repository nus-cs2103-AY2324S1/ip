package aj;

/**
 * Event class for Event Task.
 */
public class Event extends Task {
    private final String fromDt, toDt;

    public String getFromDt() {
        return this.fromDt;
    }

    public String getToDt() {
        return this.toDt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDt + " to: " + this.toDt + ")";
    }

    Event(String taskName, boolean isMarked, String fromDt, String toDt) {
        super(taskName,
                isMarked);
        this.fromDt = fromDt;
        this.toDt = toDt;
    }
}
