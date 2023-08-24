public class EventTask extends Task {
    String startDateTime;
    String endDateTime;
    public EventTask(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "]" + this.description
                + " (from: " + startDateTime + " to:" + endDateTime + ")";
    }
}
