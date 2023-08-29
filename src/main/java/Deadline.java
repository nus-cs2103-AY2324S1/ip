import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate dueDate;
    public Deadline(String description, String dueDate) throws DateTimeParseException {
        super(description);
        this.dueDate = LocalDate.parse(dueDate);
    }

    @Override
    public String toLogString() {
        return String.format("D|%s|%s|%s", (isDone ? "X" : "O"), description, dueDate);
    }

    @Override
    public String toString() {
        String taskString = String.format(" (by: %s)", dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return "[D]" + super.toString() + taskString;
    }
}

