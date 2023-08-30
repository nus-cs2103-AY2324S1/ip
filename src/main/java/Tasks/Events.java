package Tasks;

public class Events extends Task {
    // additional start and end time fields for events
    String startDate;
    String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description);
        super.taskType = 'T';
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // getters for start and end times
    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}
