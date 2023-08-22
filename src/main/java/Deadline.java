public class Deadline extends Task {
    private final String deadline;
    public Deadline(String deadlineName, String deadline) {
        super(deadlineName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
