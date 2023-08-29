package task;

public class Deadline extends Task {
    private final String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
    }
}