public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.getStatusIcon(),
                this.description,
                this.start,
                this.end
        );
    }

    @Override
    public String toFile() {
        return String.format("E | %s | %s | %s | %s",
                this.isDone ? "1" : "0", this.description, this.start, this.end);
    }

}
