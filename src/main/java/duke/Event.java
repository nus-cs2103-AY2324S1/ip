package duke;
public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String name, boolean isMarked, String startTime, String endTime) {
        super(name, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String status = isMarked ? "[X]" : "[ ]";
        return "[E]" + status + " " + name + " (from: " + startTime + " to: " + endTime + ")";
    }
}
