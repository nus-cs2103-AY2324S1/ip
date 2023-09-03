package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime time) {
        super(taskName);
        this.dateTime = time;
    }

    public Deadline(String taskName, boolean done, LocalDateTime time) {
        super(taskName, done);
        this.dateTime = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDate = dateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

}
