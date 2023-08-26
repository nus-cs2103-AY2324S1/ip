import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String description, LocalDate deadline) throws SpotException {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDate deadline) throws SpotException {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ")";
    }

    @Override
    public String toLine() {
        return " D | " + super.toLine() + " | "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public boolean fallsOn(LocalDate date) {
        return date.isEqual(this.deadline);
    }
}
