public class Event extends Task {
    private String from;
    private String to;

    private Event(String taskName, String from, String to) throws IncompleteDescriptionException {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public static Event create(String taskName, String from, String to) throws IncompleteDescriptionException {
        if (from.isBlank() || to.isBlank()) throw new IncompleteDescriptionException();
        return new Event(taskName, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
