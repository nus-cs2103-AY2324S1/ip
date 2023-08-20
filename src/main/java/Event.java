public class Event extends Task {

    protected String startData;
    protected String endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startData = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startData + " to: " + endDate + ")";
    }
}
