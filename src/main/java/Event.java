public class Event extends Task {
    protected String end;
    protected String start;

    public Event(String description, String start, String end) {
        super(description);
        this.end = end;
        this.start = start;
    }

    @Override
    public String store(){
        return String.format("E | %s | %s | %s - %s", this.isDone, this.description, this.start, this.end);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.start, this.end);
//        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}

