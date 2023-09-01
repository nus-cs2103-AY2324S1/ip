import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime dueDate;

    public Deadline(String description, boolean isDone, LocalDateTime dueDate) {

        super(description, isDone);
        this.dueDate = dueDate;
    }

    @Override
    public String contentLine() {
        return "D" + super.contentLine() + "/" + this.dueDate.toString();
    }

    @Override
    public String toString() {

        String result = "[D]" + super.toString() + " (by: " + formatDate(this.dueDate) + ")";
        return result;
    }

    public String formatDate(LocalDateTime l) {
        return l.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));
    }
}
