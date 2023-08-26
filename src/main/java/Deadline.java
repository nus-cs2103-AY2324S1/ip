import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String deadline;
    private LocalDate ld;

    private Deadline(String taskName, String deadline) throws IncompleteDescriptionException {
        super(taskName);
        try {
            this.ld = LocalDate.parse(deadline);
        } catch (DateTimeParseException ignored) {}
        this.deadline = deadline;
    }

    public static Deadline create(String taskName, String deadline) throws IncompleteDescriptionException {
        if (deadline.isBlank()) throw new IncompleteDescriptionException();
        return new Deadline(taskName, deadline);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "D" + " | " + isDoneChar + " | " + this.taskName + " | " + this.deadline;
    }

    @Override
    public String toString() {
        if (this.ld != null) {
            return "[D]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}