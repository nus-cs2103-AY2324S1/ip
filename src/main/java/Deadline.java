import java.time.LocalDate;

public class Deadline extends Task {
    protected DateTime deadline;
    public Deadline(String description, String by) {
        super(description);
        this.deadline = new DateTime(by);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDate() + ")";
    }
}
