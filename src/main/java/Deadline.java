import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = this.convertToLocalDateTime(by);
    }

    @Override public String getString() throws DateTimeException {
        return "[D]" + super.getString() + " (by: " + convertDateTimeToString(by) + ")";
    }

    @Override public String getFileString() {
        return "D|" + super.getFileString() + "|" + by.toString().replace('T', ' ');
    }
}
