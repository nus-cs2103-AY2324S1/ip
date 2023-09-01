package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime due;
    public Deadline(String details, LocalDateTime due) {
        super(details);
        this.due = due;
    }

    public Deadline(String details, boolean isCompleted, LocalDateTime due) {
        super(details, isCompleted);
        this.due = due;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "[D]" + super.toString() + " (by: " +
                this.due.toLocalDate().format(dateFormatter) + " " +
                this.due.toLocalTime().format(timeFormatter) + ")";
    }
}
