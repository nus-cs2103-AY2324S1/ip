public class DeadlineTask extends Task {
    private String endDate;
    public DeadlineTask(String taskName, String endDate) {
        super(taskName, TaskType.DEADLINE);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), endDate);
    }
}
