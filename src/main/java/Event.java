public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event initializeFromInput(String input) throws EmptyDescriptionException {
        try {
            String processed = input.split("event")[1];
            String description = processed.split("/from")[0].strip();
            String from = processed.split("/from")[1].split("/to")[0].strip();
            String to = processed.split("/to")[1].strip();
            return new Event(description, from, to);
        } catch (Exception e) {
            throw new EmptyDescriptionException("event", "event project meeting /from Mon 2pm /to 4pm");
        }
    }

    public static Event initializeFromStorage(String input) {
        String[] processed = input.split("\\(");
        String taskName = processed[0].trim();
        String from = processed[1].split("from:")[1].split("to:")[0].trim();
        String to = processed[1].split("to:")[1].split("\\)")[0].trim();
        return new Event(taskName, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
