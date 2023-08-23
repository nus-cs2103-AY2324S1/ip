public class Event extends Task {
    private String startTime;
    private String endTime;
    private String symbol = "[E]";

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}