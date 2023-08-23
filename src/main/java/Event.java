public class Event extends Task {
    private String startTime;
    private String endTime;
    public Event(String title, String startTime, String endTime) throws DukeException {
        super(title);
        if (startTime.isBlank()) {
            throw new DukeException("   Start time of an event cannot be blank...\n--------------------------------");
        }
        this.startTime = startTime;
        if (endTime.isBlank()) {
            throw new DukeException("   End time of an event cannot be blank...\n--------------------------------");
        }
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        return "Event";
    }
@Override
    public String getStatus() {
        return "[E]" + super.getStatus() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
