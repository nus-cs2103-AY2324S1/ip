package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime dueDate;
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return String.format("[D][%s] %s (by: %s)", super.getStatusIcon(), description, dueDate.format(formatter));
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("D | %s | %s | %s", super.isDoneString(), description, dueDate.format(formatter));
    }
}
