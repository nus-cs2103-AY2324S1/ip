public class EventTask extends Task {
    private String from;
    private String to;

    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + from + " | " + to;
    }
}
