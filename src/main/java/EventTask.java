public class EventTask extends Task {
    private final String startTime;
    private final String endTime;
    public EventTask(String desc, String startTime, String endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    @Override
    public String getSaveFormat() {
        return String.format(
                "E | %s | %s | %s",
                super.getSaveFormat(),
                startTime,
                endTime
        );
    }
}
