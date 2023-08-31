public class Event extends Task {

    private String startTime;
    private String endTime;
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String storeFormat() {
        String taskType = "D";
        String isTaskDone;

        if (this.isDone) {
            isTaskDone = "1";
        } else {
            isTaskDone = "0";
        }

        return (taskType + " | " + isTaskDone + " | " + this.description + " | " + this.startTime + " | " +
                this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startTime, endTime);
    }
}
