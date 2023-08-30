import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDateTime localDateTime;

    public Deadline(String description, LocalDateTime localDateTime) {
        super(description);
        this.localDateTime = localDateTime;
    }

    public Deadline(String description, LocalDateTime localDateTime, Boolean isDone) {
        super(description, isDone);
        this.localDateTime = localDateTime;
    }

    @Override
     public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
