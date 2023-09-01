import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private static final String TYPE = "[D]";
    protected LocalDate deadline;

    public Deadline(String task, String deadline) throws DateTimeParseException {
        super(task);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public boolean isOnDate(LocalDate date) {
        return date.isEqual(this.deadline);
    }

    @Override
    public String toSaveFormat() {
        return "Deadline | " + super.toSaveFormat() + " | " + this.deadline;
    }

    @Override
    public String toString() {
        return Deadline.TYPE + super.toString() + " (by: " + this.deadline.format(
                DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
