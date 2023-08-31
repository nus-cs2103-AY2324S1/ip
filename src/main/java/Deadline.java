import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("E, dd MMM yyyy");
        return "[D]" + super.toString() + " (by: " +
                dateFormatter.format(this.due.toLocalDate()) + " " +
                this.due.toLocalTime().toString() + ")";
    }
}
