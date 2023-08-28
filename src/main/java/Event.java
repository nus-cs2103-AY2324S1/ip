public class Event extends Task {
    protected String dateFrom;
    protected String dateTo;
    public Event(String description, String dateFrom, String dateTo) {
        super(description);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    @Override
    public String toLogString() {
        return String.format("E|%s|%s|%s|%s", (isDone ? "X" : "O"), description, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", dateFrom, dateTo);
    }
}
