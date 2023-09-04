public class Event extends Task {
    protected DateTime start;
    protected DateTime end;
    public Event(String description, String from, String to) {
        super(description);
        start = new DateTime(from);
        end = new DateTime(to);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.getDate() + "to: " + end.getDate() + ")";
    }
}
