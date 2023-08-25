package tasks;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline, boolean completed) {
        super(description, completed);
        this.deadline = deadline;
    }

    @Override
    public String getFileFormat() {
        return String.format("D | %s | %s", super.getFileFormat(), this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", this.deadline);
    }
}
