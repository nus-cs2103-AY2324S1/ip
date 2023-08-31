import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    protected LocalDateTime dateTime;
    protected LocalDate date;

    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toFileString() {
        if (dateTime != null) {
            return "D | " + super.toFileString() + " | "
                    + dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return "D | " + super.toFileString() + " | " + date.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    @Override
    public String toString() {
        String formattedDate;

        if (dateTime != null) {
            formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        } else {
            formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

}
