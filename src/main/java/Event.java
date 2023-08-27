public class Event extends Task {
    private String fromDate;
    private String toDate;
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + this.description
                + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public String fileString() {
        return "E|" + (this.isDone? 1: 0) + "|" + this.description + "|" + fromDate
                + "|" + toDate;
    }
}

