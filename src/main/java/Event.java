public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event initializeFromInput(String input) {
        String processed = input.split("event")[1];
        String description = processed.split("/from")[0].strip();
        String from = processed.split("/from")[1].split("/to")[0].strip();
        String to = processed.split("/to")[1].strip();
        return new Event(description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
