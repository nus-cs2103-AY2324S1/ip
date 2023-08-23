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
                + "(from:" + fromDate +" to:" + toDate + ")";
    }
}

