import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(int status, String task, LocalDateTime date) {
        super(status, task);
        this.date = date;
    }

    @Override
    public String convertTask() {
        return "D | " +  super.getStatus() + " | " + super.getTask() +
                " | " + this.date.format(formatter);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}
