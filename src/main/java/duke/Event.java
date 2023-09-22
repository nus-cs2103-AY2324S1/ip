package duke;

public class Event extends Task {
    private String startTime;
    private String endTime;

    //Event objects inherit from tasks and has additional attributes start and end time

    public Event(String name, boolean isMarked, String startTime, String endTime) {
        super(name, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getter of start time of event
    public String getStartTime() {
        return startTime;
    }

    // setter of start time for event
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // getter of end time for event
    public String getEndTime() {
        return endTime;
    }

    // setter of end time for event
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String status = isMarked ? "[X]" : "[ ]";
        return "[E]" + status + " " + name + " (from: " + startTime + " to: " + endTime + ")";
    }
}
