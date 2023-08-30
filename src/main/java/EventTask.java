public class EventTask extends Task {
    private String startDate;
    private String endDate;

    public EventTask(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return "Event";
    }

    public String getDateTime() {
        return this.startDate + "," + this.endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
