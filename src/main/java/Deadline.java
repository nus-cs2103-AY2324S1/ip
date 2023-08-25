public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) throws SpotException {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, String deadline) throws SpotException {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toLine() {
        return " D | " + super.toLine() + " | " + this.deadline;
    }
}
