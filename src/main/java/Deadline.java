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

    @Override
    public String serialize() {
        return String.format("deadline|%c|%s|%s", (this.isDone() ? '1' : '0'), getTaskName(), this.deadline);
    }
}
