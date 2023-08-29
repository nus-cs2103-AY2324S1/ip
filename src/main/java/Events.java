public class Events extends Task {
    protected String start;
    protected String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format(
                "| E |%s (from: %s to: %s)", super.toString(), start, end);
    }
}
