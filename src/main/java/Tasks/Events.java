package Tasks;

public class Events extends Task {
    // additional start and end time fields for events
    String startTime;
    String endTime;
    public Events(String description, String startTime, String endTime) {
        super(description);
        super.taskType = 'T';
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // getters for start and end times
    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }
}
