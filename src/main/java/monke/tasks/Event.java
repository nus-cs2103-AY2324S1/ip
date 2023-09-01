package monke.tasks;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    @Override
    public String formatData() {
        return String.format("E | %d | %s | %s | %s\n",
                this.isDone ? 1 : 0, this.description, this.start, this.end);
    }
}
