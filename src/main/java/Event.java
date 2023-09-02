public class Event extends Task {
    private DateTimeHandler start;
    private DateTimeHandler end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = new DateTimeHandler(start);
        this.end = new DateTimeHandler(end);
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + start.toString() + " - " + end.toString();
    }
}
