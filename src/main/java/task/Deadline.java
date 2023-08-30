package task;

public class Deadline extends Task {
    private final String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }
    public String getDeadlineBy() {
        return deadlineBy;
    }

    @Override
    public String toFileString() {
        // Format: [Type] | [DoneStatus] | [Description] | [Deadline]
        // Example: D | 0 | Submit report | 2023-09-01
        String type = "D";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " + deadlineBy;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
    }
}