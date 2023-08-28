public class Event extends Task {
    private String from;
    private String to;

    public Event(String detail, String from, String to) {
        super(detail);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)",
            super.toString(), 
            this.from, 
            this.to
        );
    }

    @Override
    public String toFileFormatString() {
        return String.format(
            "E|%s|%s|%s",
            super.toFileFormatString(),
            this.from, 
            this.to
        );
    }
}
