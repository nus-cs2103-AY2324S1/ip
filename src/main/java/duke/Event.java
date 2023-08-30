package duke;

public class Event extends Task {
    private String startTime;
    private String endTime;
    private String symbol = "[E]";

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDetails() {
        return "(from: " + this.startTime + " to: " + this.endTime + ")";
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName() + " " + this.getDetails();
    }

    @Override
    public String newFormat() {
        return this.symbol + " | " + this.getInt() + " | " + this.getName() + " | " + this.getDetails();
    }
}