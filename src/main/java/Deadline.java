import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String dateString, String timeString) {
        super(description);
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        LocalDateTime by = LocalDateTime.of(date, time);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm");
        String byFormatted = this.by.format(formatter);
        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}
