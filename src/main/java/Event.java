public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String fromm, String too) {
        super(task);
        from = fromm;
        to = too;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
