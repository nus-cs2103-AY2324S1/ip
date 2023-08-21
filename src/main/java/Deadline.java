public class Deadline extends Task {
    private final String deadline;

    public Deadline(String deadlineDesc, String deadline) {
        super(deadlineDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "by: " + this.deadline;
    }
}
