public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + deadline + ")";
    }
}
