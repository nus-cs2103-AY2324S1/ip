import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime dueDate;
    public Deadline(String desc, LocalDateTime dueDate) {
        super(desc);
        this.dueDate = dueDate;
    }

    public String formatDateTime(LocalDateTime dt) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

        return dt.format(dateTimeFormatter);
    }

    @Override
    public String toSavedString() {
        return String.format("[D] %s//%s//", super.toSavedString(), formatDateTime(this.dueDate));
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), formatDateTime(this.dueDate));
    }
}
