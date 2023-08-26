public class Event extends Task{
    protected String fromTime;
    protected String toTime;

    public Event(String description, String fromTime, String toTime) throws AlexException {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromTime + " to: " + toTime + ")";
    }
}
