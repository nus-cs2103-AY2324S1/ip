public class EventTask extends Task {
    private String startDate;
    private String endDate;
    public EventTask(String taskName, String startDate, String endDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), startDate, endDate);
    }
}
