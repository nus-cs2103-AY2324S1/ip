import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String checkBox = this.done ? "[D][X] " : "[D][ ] ";
        String description = String.format("%s (by: %s)", this.task,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        return checkBox + description;
    }
}
