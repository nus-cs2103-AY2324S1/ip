public class Event extends Task {
    private String start;
    private String end;

    public Event(String detail, String start, String end) {
        super(detail);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)",
            super.toString(), start, end
        );
    }
}
