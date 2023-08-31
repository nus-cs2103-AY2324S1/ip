import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + " (by: " + deadline.format(super.getOutputFormat()) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + deadline.format(super.getOutputFormat());
    }

    @Override
    public String toString() {
        return super.toString() + " | " + deadline.format(super.getOutputFormat());
    }
}
