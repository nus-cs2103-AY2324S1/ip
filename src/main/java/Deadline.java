public class Deadline extends Task {
    private final String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(String taskName, String deadline, boolean isDone) {
        super(taskName, isDone);
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

    @Override
    public String toSaveFormat() {
        return "D | " + (super.isDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + this.deadline;
    }
}
