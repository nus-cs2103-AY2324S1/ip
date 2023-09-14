package duke;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toTaskListEntry() {
        return String.format("E | %d | %s | %s | %s", this.getIsDone() ? 1 : 0, this.getName(), this.from, this.to);
    }
}
