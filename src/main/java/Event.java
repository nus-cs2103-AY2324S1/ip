public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event (String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.startTime, this.endTime);
    }
}

