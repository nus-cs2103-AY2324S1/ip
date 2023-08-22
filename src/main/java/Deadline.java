public class Deadline extends Task {
    private final String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineTime);
    }
}
