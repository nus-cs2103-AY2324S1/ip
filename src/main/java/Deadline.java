import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String by) {
        super(description);
        this.deadline = this.convertToDateTime(by);
    }

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String displayDeadline() {
        return this.displayTime(this.deadline);
    }

    public String saveDeadline() {
        return this.saveTime(this.deadline);
    }

    @Override
    public String getOutputString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, this.saveDeadline());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.displayDeadline() + ")";
    }
}
