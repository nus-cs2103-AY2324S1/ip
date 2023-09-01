public class Event extends Task{
    protected String fromTime;
    protected String toTime;

    public Event(String description, String fromTime, String toTime) throws AlexException {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getFromTime() {
        return this.fromTime;
    }

    public String getToTime() {
        return this.toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromTime + " to: " + toTime + ")";
    }
}
