public class Deadline extends Task {
    private String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "[D]";
    }
    @Override
    public String getTaskTime() {
        return " (by: " + this.deadline + ")";
    }
}
